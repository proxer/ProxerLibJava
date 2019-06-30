package me.proxer.library

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.mockwebserver.MockWebServer

/**
 * @author Ruben Gees
 */
class MockWebServerUrlRewriteInterceptor(private val server: MockWebServer) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldUrl = chain.request().url
        val serverUrl = server.url(oldUrl.encodedPath)

        val newUrl = oldUrl.newBuilder()
            .scheme(oldUrl.scheme)
            .host(serverUrl.host)
            .port(serverUrl.port)
            .build()

        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}
