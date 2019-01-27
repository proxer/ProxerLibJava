package me.proxer.library.api

import me.proxer.library.util.ProxerUrls
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Ruben Gees
 */
internal class HeaderInterceptor(private val apiKey: String, private val userAgent: String) : Interceptor {

    private companion object {
        private const val API_KEY_HEADER = "proxer-api-key"
        private const val USER_AGENT_HEADER = "User-Agent"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        if (ProxerUrls.hasProxerHost(oldRequest.url())) {
            val newRequestBuilder = oldRequest.newBuilder()

            if (oldRequest.url().host() == ProxerUrls.apiBase.host()) {
                newRequestBuilder.header(API_KEY_HEADER, apiKey)
            }

            newRequestBuilder.header(USER_AGENT_HEADER, userAgent)

            return chain.proceed(newRequestBuilder.build())
        } else {
            throw IllegalArgumentException("Only use ProxerLib's OkHttp instance with Proxer.Me URLs!")
        }
    }
}
