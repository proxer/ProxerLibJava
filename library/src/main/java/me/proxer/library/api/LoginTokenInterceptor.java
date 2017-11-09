package me.proxer.library.api;

import com.squareup.moshi.JsonDataException;
import me.proxer.library.api.ProxerException.ServerErrorType;
import me.proxer.library.util.ProxerUrls;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;

/**
 * @author Ruben Gees
 */
final class LoginTokenInterceptor implements Interceptor {

    private static final String LOGIN_TOKEN_HEADER = "proxer-api-token";
    private static final int MAX_PEEK_BYTE_COUNT = 1024;

    private static final Pattern LOGIN_TOKEN_PATTERN = Pattern.compile("\"token\":.*?\"(.+?)\"", DOTALL);
    private static final Pattern ERROR_PATTERN = Pattern.compile("\"code\":.*?(\\d+\b?)", DOTALL);

    private static final List<String> LOGIN_PATH = ProxerUrls.apiBase().newBuilder()
            .addPathSegment("user")
            .addPathSegment("login")
            .build()
            .pathSegments();

    private static final List<String> LOGOUT_PATH = ProxerUrls.apiBase().newBuilder()
            .addPathSegment("user")
            .addPathSegment("logout")
            .build()
            .pathSegments();

    private final LoginTokenManager loginTokenManager;

    LoginTokenInterceptor(final LoginTokenManager loginTokenManager) {
        this.loginTokenManager = loginTokenManager;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request oldRequest = chain.request();

        if (ProxerUrls.hasProxerHost(oldRequest.url())) {
            final Request.Builder newRequestBuilder = oldRequest.newBuilder();
            final String loginToken = loginTokenManager.provide();

            if (loginToken != null) {
                newRequestBuilder.addHeader(LOGIN_TOKEN_HEADER, loginToken);
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

        if (errorMatcher.find()) {
            final int errorCode = Integer.parseInt(errorMatcher.group(1));
            final ServerErrorType errorType = ServerErrorType.fromErrorCodeOrNull(errorCode);

            if (errorType != null && isLoginError(errorType)) {
                loginTokenManager.persist(null);
            }
        } else if (url.pathSegments().equals(LOGIN_PATH)) {
            final Matcher loginTokenMatcher = LOGIN_TOKEN_PATTERN.matcher(responseBody);

            if (loginTokenMatcher.find()) {
                loginTokenManager.persist(loginTokenMatcher.group(1).trim());
            } else {
                throw new JsonDataException("No token found after successful login.");
            }
        } else if (url.pathSegments().equals(LOGOUT_PATH)) {
            loginTokenManager.persist(null);
        }
    }

    private String peekResponseBody(final Response response) throws IOException {
        final ResponseBody safeBody = response.body();

        return safeBody != null && safeBody.contentLength() != 0 ? response.peekBody(MAX_PEEK_BYTE_COUNT).string() : "";
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
                return true;
            default:
                return false;
        }
    }
}
