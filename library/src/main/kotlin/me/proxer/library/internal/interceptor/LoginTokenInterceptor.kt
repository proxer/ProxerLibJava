package me.proxer.library.internal.interceptor

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import me.proxer.library.LoginTokenManager
import me.proxer.library.ProxerException.ServerErrorType
import me.proxer.library.util.ProxerUrls
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * @author Ruben Gees
 */
internal class LoginTokenInterceptor(private val loginTokenManager: LoginTokenManager) : Interceptor {

    private companion object {
        private const val LOGIN_TOKEN_HEADER = "proxer-api-token"
        private const val MAX_PEEK_BYTE_COUNT = 1_048_576L

        private val OPTIONS = JsonReader.Options.of("code", "data")
        private val TOKEN_OPTIONS = JsonReader.Options.of("token")

        private val LOGIN_URL = ProxerUrls.apiBase.newBuilder()
            .addPathSegment("user")
            .addPathSegment("login")
            .build()

        private val LOGOUT_URL = ProxerUrls.apiBase.newBuilder()
            .addPathSegment("user")
            .addPathSegment("logout")
            .build()
    }

    private val lock = ReentrantLock()

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        if (oldRequest.url.host == ProxerUrls.apiBase.host) {
            val newRequestBuilder = oldRequest.newBuilder()

            if (oldRequest.url != LOGIN_URL) {
                val loginToken = lock.withLock {
                    loginTokenManager.provide()
                }

                if (loginToken != null) {
                    newRequestBuilder.addHeader(LOGIN_TOKEN_HEADER, loginToken)
                }
            }

            return handleResponse(chain.proceed(newRequestBuilder.build()))
        } else {
            return chain.proceed(oldRequest)
        }
    }

    private fun handleResponse(response: Response): Response {
        if (response.isSuccessful) {
            val (errorCode, token) = peekResponse(response)

            lock.withLock {
                val errorType = errorCode?.let { ServerErrorType.fromErrorCode(it) }

                if (errorType != null) {
                    if (errorType.isLoginError) {
                        loginTokenManager.persist(null)
                    }
                } else if (response.request.url.pathSegments == LOGIN_URL.pathSegments) {
                    if (token != null) {
                        loginTokenManager.persist(token)
                    } else {
                        throw JsonDataException("No token found after successful login.")
                    }
                } else if (response.request.url.pathSegments == LOGOUT_URL.pathSegments) {
                    loginTokenManager.persist(null)
                }
            }
        }

        return response
    }

    private fun peekResponse(response: Response): PeekedResponse {
        if (response.body == null) return PeekedResponse()

        val reader = JsonReader.of(response.peekBody(MAX_PEEK_BYTE_COUNT).source())
            .also { it.beginObject() }

        var errorCode: Int? = null
        var token: String? = null

        while (reader.hasNext() && errorCode == null) {
            when (reader.selectName(OPTIONS)) {
                0 -> errorCode = reader.nextInt()
                1 -> when (reader.peek()) {
                    JsonReader.Token.BEGIN_OBJECT -> token = peekForToken(reader)
                    else -> reader.skipValue()
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        return PeekedResponse(errorCode, token)
    }

    private fun peekForToken(reader: JsonReader): String? {
        reader.beginObject()

        var token: String? = null

        while (reader.hasNext()) {
            when (reader.selectName(TOKEN_OPTIONS)) {
                0 -> token = reader.nextString()
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        reader.endObject()

        return token
    }

    private data class PeekedResponse(val errorCode: Int? = null, val token: String? = null)
}
