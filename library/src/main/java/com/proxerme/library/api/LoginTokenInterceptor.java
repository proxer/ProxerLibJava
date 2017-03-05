package com.proxerme.library.api;

import com.proxerme.library.util.ProxerUrls;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class LoginTokenInterceptor implements Interceptor {

    private static final String LOGIN_TOKEN_HEADER = "proxer-api-token";
    private static final int MAX_PEEK_BYTE_COUNT = 1048576; // 1MB

    private static final Pattern loginTokenPattern = Pattern.compile("\"token\":\"(.+)\"");
    private static final Pattern errorPattern = Pattern.compile("\"error\":(.+),");

    private static final String NO_ERROR = "0";

    private final LoginTokenManager loginTokenManager;

    LoginTokenInterceptor(@Nullable LoginTokenManager loginTokenManager) {
        this.loginTokenManager = loginTokenManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (loginTokenManager != null) {
            final Request.Builder newRequestBuilder = chain.request().newBuilder();
            final String loginToken = loginTokenManager.provide();

            if (loginToken != null) {
                newRequestBuilder.addHeader(LOGIN_TOKEN_HEADER, loginToken);
            }

            final Response response = chain.proceed(newRequestBuilder.build());

            if (response.isSuccessful()) {
                final HttpUrl url = response.request().url();

                if (url.equals(ProxerUrls.apiBase().newBuilder()
                        .addPathSegment("user")
                        .addPathSegment("login")
                        .build())) {
                    final Matcher matcher = loginTokenPattern.matcher(response.peekBody(MAX_PEEK_BYTE_COUNT)
                            .string());

                    if (matcher.find()) {
                        loginTokenManager.persist(matcher.group(1));
                    }
                } else if (url.equals(ProxerUrls.apiBase().newBuilder()
                        .addPathSegment("user")
                        .addPathSegment("logout")
                        .build())) {
                    final Matcher matcher = errorPattern.matcher(response.peekBody(MAX_PEEK_BYTE_COUNT)
                            .string());

                    if (matcher.find() && matcher.group(1).equals(NO_ERROR)) {
                        loginTokenManager.persist(null);
                    }
                }
            }

            return response;
        } else {
            return chain.proceed(chain.request());
        }
    }
}
