package me.proxer.library.internal.interceptor

import me.proxer.library.util.ProxerUrls
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @author Ruben Gees
 */
internal class HttpsEnforcingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return if (ProxerUrls.hasProxerHost(request.url())) {
            if (!request.isHttps) {
                chain.proceed(request.enforceHttps())
            } else {
                chain.proceed(request)
            }
        } else {
            throw IllegalArgumentException("Only use ProxerLib's OkHttp instance with Proxer.Me URLs!")
        }
    }

    private fun Request.enforceHttps() = newBuilder()
        .url(url().enforceHttps())
        .build()

    private fun HttpUrl.enforceHttps() = newBuilder()
        .scheme("https")
        .build()
}
