package me.proxer.library.api;

import me.proxer.library.util.ProxerUrls;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author Ruben Gees
 */
class HttpsEnforcingInterceptor implements Interceptor {

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request request = chain.request();

        final boolean appliesToUrl = ProxerUrls.hasProxerWebOrCdnHost(request.url())
                || ProxerUrls.hasProxerMangaHost(request.url());

        if (appliesToUrl && !request.isHttps()) {
            return chain.proceed(request.newBuilder()
                    .url(request.url().newBuilder()
                            .scheme("https")
                            .build())
                    .build());
        } else {
            return chain.proceed(request);
        }
    }
}
