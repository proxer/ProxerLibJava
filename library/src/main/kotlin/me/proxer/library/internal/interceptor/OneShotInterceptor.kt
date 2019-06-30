package me.proxer.library.internal.interceptor

import me.proxer.library.internal.OneShotDelegatingRequestBody
import me.proxer.library.util.ProxerUrls.hasProxerHost
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Ruben Gees
 */
internal class OneShotInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val body = oldRequest.body

        if (oldRequest.url.hasProxerHost) {
            val newRequest = if (body != null) {
                oldRequest.newBuilder()
                    .method(oldRequest.method, OneShotDelegatingRequestBody(body))
                    .build()
            } else {
                oldRequest
            }

            return chain.proceed(newRequest)
        } else {
            throw IllegalArgumentException("Only use ProxerLib's OkHttp instance with Proxer.Me URLs!")
        }
    }
}
