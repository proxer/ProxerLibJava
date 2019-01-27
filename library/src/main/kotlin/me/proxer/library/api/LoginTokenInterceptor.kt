package me.proxer.library.api

import com.squareup.moshi.JsonDataException
import me.proxer.library.api.ProxerException.ServerErrorType
import me.proxer.library.util.ProxerUrls
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Ruben Gees
 */
internal class LoginTokenInterceptor(private val loginTokenManager: LoginTokenManager) : Interceptor {

    private companion object {
        private const val LOGIN_TOKEN_HEADER = "proxer-api-token"
        private const val MAX_PEEK_BYTE_COUNT = 512L

        private val LOGIN_TOKEN_PATTERN = Regex("\"token\":.*?\"(.{255})\"", RegexOption.DOT_MATCHES_ALL)
        private val ERROR_PATTERN = Regex("\"code\":.*?(\\d+\b?)", RegexOption.DOT_MATCHES_ALL)

        private val LOGIN_URL = ProxerUrls.apiBase.newBuilder()
            .addPathSegment("user")
            .addPathSegment("login")
            .build()

        private val LOGOUT_URL = ProxerUrls.apiBase.newBuilder()
            .addPathSegment("user")
            .addPathSegment("logout")
            .build()
    }

    private val lock = Any()

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        if (oldRequest.url().host() == ProxerUrls.apiBase.host()) {
            val newRequestBuilder = oldRequest.newBuilder()

            if (oldRequest.url() != LOGIN_URL) {
                val loginToken = synchronized(lock) {
                    loginTokenManager.provide()
                }

                if (loginToken != null) {
                    newRequestBuilder.addHeader(LOGIN_TOKEN_HEADER, loginToken)
                }
            }

            val response = chain.proceed(newRequestBuilder.build())

            if (response.isSuccessful) {
                handleResponse(response)
            }

            return response
        } else {
            return chain.proceed(oldRequest)
        }
    }

    private fun handleResponse(response: Response) {
        val responseBody = peekResponseBody(response)
        val url = response.request().url()

        synchronized(lock) {
            val existingLoginToken = loginTokenManager.provide()
            val potentialError = ERROR_PATTERN.find(responseBody)

            if (potentialError != null) {
                val errorCode = potentialError.groupValues[1].toInt()
                val errorType = ServerErrorType.fromErrorCode(errorCode)

                if (errorType != ServerErrorType.UNKNOWN && existingLoginToken != null && isLoginError(errorType)) {
                    loginTokenManager.persist(null)
                }
            } else if (url.pathSegments() == LOGIN_URL.pathSegments()) {
                val token = LOGIN_TOKEN_PATTERN.find(responseBody)

                if (token != null) {
                    loginTokenManager.persist(token.groupValues[1])
                } else {
                    throw JsonDataException("No token found after successful login.")
                }
            } else if (url.pathSegments() == LOGOUT_URL.pathSegments()) {
                loginTokenManager.persist(null)
            }
        }
    }

    private fun peekResponseBody(response: Response): String {
        return response.body()?.source()
            ?.let {
                it.request(MAX_PEEK_BYTE_COUNT)

                it.buffer.snapshot().utf8()
            }
            ?: ""
    }

    private fun isLoginError(errorType: ServerErrorType): Boolean {
        return when (errorType) {
            ServerErrorType.INVALID_TOKEN,
            ServerErrorType.NOTIFICATIONS_LOGIN_REQUIRED,
            ServerErrorType.UCP_LOGIN_REQUIRED,
            ServerErrorType.INFO_LOGIN_REQUIRED,
            ServerErrorType.LOGIN_ALREADY_LOGGED_IN,
            ServerErrorType.LOGIN_DIFFERENT_USER_ALREADY_LOGGED_IN,
            ServerErrorType.MESSAGES_LOGIN_REQUIRED,
            ServerErrorType.CHAT_LOGIN_REQUIRED,
            ServerErrorType.USER_2FA_SECRET_REQUIRED,
            ServerErrorType.ANIME_LOGIN_REQUIRED -> true

            else -> false
        }
    }
}
