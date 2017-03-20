package me.proxer.library.api;

import me.proxer.library.util.ProxerUrls;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * @author Ruben Gees
 */
final class HeaderInterceptor implements Interceptor {

    private static final String API_KEY_HEADER = "proxer-api-key";
    private static final String HEADER_USER_AGENT = "User-Agent";

    private final String apiKey;
    private final String userAgent;

    HeaderInterceptor(@NotNull final String apiKey, @Nullable final String userAgent) {
        this.apiKey = apiKey;
        this.userAgent = userAgent;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request oldRequest = chain.request();

        if (oldRequest.url().host().equals(ProxerUrls.apiBase().host())) {
            final Request.Builder newRequestBuilder = chain.request().newBuilder().addHeader(API_KEY_HEADER, apiKey);

            if (userAgent != null) {
                newRequestBuilder.addHeader(HEADER_USER_AGENT, userAgent);
            }

            return chain.proceed(newRequestBuilder.build());
        } else {
            return chain.proceed(oldRequest);
        }
    }
}
