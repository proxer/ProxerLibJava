package me.proxer.library.api;

import com.squareup.moshi.JsonDataException;
import me.proxer.library.api.ProxerException.ServerErrorType;
import me.proxer.library.util.ProxerUrls;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;

/**
 * @author Ruben Gees
 */
final class LoginTokenInterceptor implements Interceptor {

    private static final String LOGIN_TOKEN_HEADER = "proxer-api-token";
    private static final int MAX_PEEK_BYTE_COUNT = 512;

    private static final Pattern LOGIN_TOKEN_PATTERN = Pattern.compile("\"token\":.*?\"(.{255})\"", DOTALL);
    private static final Pattern ERROR_PATTERN = Pattern.compile("\"code\":.*?(\\d+\b?)", DOTALL);

    private static final HttpUrl LOGIN_URL = ProxerUrls.apiBase().newBuilder()
            .addPathSegment("user")
            .addPathSegment("login")
            .build();

    private static final HttpUrl LOGOUT_URL = ProxerUrls.apiBase().newBuilder()
            .addPathSegment("user")
            .addPathSegment("logout")
            .build();

    private final LoginTokenManager loginTokenManager;

    private final Object lock = new Object();

    LoginTokenInterceptor(final LoginTokenManager loginTokenManager) {
        this.loginTokenManager = loginTokenManager;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request oldRequest = chain.request();

        if (oldRequest.url().host().equals(ProxerUrls.apiBase().host())) {
            final Request.Builder newRequestBuilder = oldRequest.newBuilder();

            if (!oldRequest.url().equals(LOGIN_URL)) {
                final String loginToken;

                synchronized (lock) {
                    loginToken = loginTokenManager.provide();
                }

                if (loginToken != null) {
                    newRequestBuilder.addHeader(LOGIN_TOKEN_HEADER, loginToken);
                }
            }

            final Response response = chain.proceed(newRequestBuilder.build());

            if (response.isSuccessful()) {
                handleResponse(response);
            }

            return response;
        } else {
            return chain.proceed(oldRequest);
        }
    }

    private void handleResponse(final Response response) throws IOException {
        final String responseBody = peekResponseBody(response);
        final Matcher errorMatcher = ERROR_PATTERN.matcher(responseBody);
        final HttpUrl url = response.request().url();

        synchronized (lock) {
            final String existingLoginToken = loginTokenManager.provide();

            if (errorMatcher.find()) {
                final int errorCode = Integer.parseInt(errorMatcher.group(1));
                final ServerErrorType errorType = ServerErrorType.fromErrorCode(errorCode);

                if (errorType != null && existingLoginToken != null && isLoginError(errorType)) {
                    loginTokenManager.persist(null);
                }
            } else if (url.pathSegments().equals(LOGIN_URL.pathSegments())) {
                final Matcher loginTokenMatcher = LOGIN_TOKEN_PATTERN.matcher(responseBody);

                if (loginTokenMatcher.find()) {
                    loginTokenManager.persist(loginTokenMatcher.group(1));
                } else {
                    throw new JsonDataException("No token found after successful login.");
                }
            } else if (url.pathSegments().equals(LOGOUT_URL.pathSegments())) {
                loginTokenManager.persist(null);
            }
        }
    }

    private String peekResponseBody(final Response response) throws IOException {
        final ResponseBody safeBody = response.body();

        if (safeBody != null) {
            final BufferedSource source = safeBody.source();

            source.request(MAX_PEEK_BYTE_COUNT);

            return source.getBuffer().snapshot().utf8();
        }

        return "";
    }

    private boolean isLoginError(final ServerErrorType errorType) {
        switch (errorType) {
            case INVALID_TOKEN:
            case NOTIFICATIONS_LOGIN_REQUIRED:
            case UCP_LOGIN_REQUIRED:
            case INFO_LOGIN_REQUIRED:
            case LOGIN_ALREADY_LOGGED_IN:
            case LOGIN_DIFFERENT_USER_ALREADY_LOGGED_IN:
            case MESSAGES_LOGIN_REQUIRED:
            case CHAT_LOGIN_REQUIRED:
            case USER_2FA_SECRET_REQUIRED:
            case ANIME_LOGIN_REQUIRED:
                return true;
            default:
                return false;
        }
    }
}
