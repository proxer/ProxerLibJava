package com.proxerme.library.api;

import com.proxerme.library.api.ProxerException.ServerErrorType;
import com.proxerme.library.util.ProxerUrls;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
final class LoginTokenInterceptor implements Interceptor {

    private static final String LOGIN_TOKEN_HEADER = "proxer-api-token";
    private static final int MAX_PEEK_BYTE_COUNT = 1048576; // 1MB

    private static final HttpUrl loginUrl = ProxerUrls.apiBase().newBuilder()
            .addPathSegment("user")
            .addPathSegment("login")
            .build();
    private static final HttpUrl logoutUrl = ProxerUrls.apiBase().newBuilder()
            .addPathSegment("user")
            .addPathSegment("logout")
            .build();

    private static final Pattern loginTokenPattern = Pattern.compile("\"token\":\"(.+?)\"");
    private static final Pattern errorPattern = Pattern.compile("\"code\":(.+?)}");

    private final LoginTokenManager loginTokenManager;

    LoginTokenInterceptor(@Nullable LoginTokenManager loginTokenManager) {
        this.loginTokenManager = loginTokenManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request oldRequest = chain.request();

        if (loginTokenManager != null && oldRequest.url().host().equals(ProxerUrls.apiBase().host())) {
            final Request.Builder newRequestBuilder = oldRequest.newBuilder();
            final String loginToken = loginTokenManager.provide();

            if (loginToken != null) {
                newRequestBuilder.addHeader(LOGIN_TOKEN_HEADER, loginToken);
            }

            final Response response = chain.proceed(newRequestBuilder.build());

            if (response.isSuccessful()) {
                final HttpUrl url = response.request().url();
                final Matcher errorMatcher = errorPattern.matcher(response.peekBody(MAX_PEEK_BYTE_COUNT).string());
                final Integer errorCode;

                if (errorMatcher.find()) {
                    errorCode = toIntOrNull(errorMatcher.group(1));

                    if (errorCode == null) {
                        return response;
                    }
                } else {
                    errorCode = 0;
                }

                if (errorCode == 0) {
                    if (url.equals(loginUrl)) {
                        final Matcher matcher = loginTokenPattern.matcher(response.peekBody(MAX_PEEK_BYTE_COUNT)
                                .string());

                        if (matcher.find()) {
                            loginTokenManager.persist(matcher.group(1));
                        }
                    } else if (url.equals(logoutUrl)) {
                        loginTokenManager.persist(null);
                    }
                } else {
                    final ServerErrorType errorType = ServerErrorType.fromErrorCodeOrNull(errorCode);

                    if (errorType != null && isLoginError(errorType)) {
                        loginTokenManager.persist(null);
                    }
                }
            }

            return response;
        } else {
            return chain.proceed(oldRequest);
        }
    }

    @Nullable
    private Integer toIntOrNull(@NotNull String candidate) {
        try {
            return Integer.parseInt(candidate);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private boolean isLoginError(@NotNull ServerErrorType errorType) {
        switch (errorType) {
            case NOTIFICATIONS_LOGIN_REQUIRED:
            case UCP_LOGIN_REQUIRED:
            case INFO_LOGIN_REQUIRED:
            case LOGIN_ALREADY_LOGGED_IN:
            case LOGIN_DIFFERENT_USER_ALREADY_LOGGED_IN:
            case MESSAGES_LOGIN_REQUIRED:
            case CHAT_LOGIN_REQUIRED:
            case USER_2FA_SECRET_REQUIRED:
                return true;
            default:
                return false;
        }
    }
}
