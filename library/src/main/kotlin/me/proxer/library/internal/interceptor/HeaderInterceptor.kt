package me.proxer.library.internal.interceptor

import me.proxer.library.ProxerApi
import me.proxer.library.util.ProxerUrls
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Ruben Gees
 */
internal class HeaderInterceptor(private val apiKey: String, private val userAgent: String) : Interceptor {

    private companion object {
        private const val TEST_MODE_HEADER = "proxer-api-testmode"
        private const val API_KEY_HEADER = "proxer-api-key"
        private const val USER_AGENT_HEADER = "User-Agent"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        require(ProxerUrls.hasProxerHost(oldRequest.url())) {
            "Only use ProxerLib's OkHttp instance with Proxer.Me URLs!"
        }

        val newRequestBuilder = oldRequest.newBuilder()

        if (oldRequest.url().host() == ProxerUrls.apiBase.host()) {
            if (apiKey == ProxerApi.TEST_KEY) {
                newRequestBuilder.header(TEST_MODE_HEADER, "1")
            } else {
                newRequestBuilder.header(API_KEY_HEADER, apiKey)
            }
        }

        newRequestBuilder.header(USER_AGENT_HEADER, userAgent)

        return chain.proceed(newRequestBuilder.build())
    }
}
