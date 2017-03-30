package me.proxer.library.api;

import me.proxer.library.api.ProxerException.ServerErrorType;
import me.proxer.library.util.ProxerUrls;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    private static final int MAX_PEEK_BYTE_COUNT = 1048576;
    private static final Pattern LOGIN_TOKEN_PATTERN = Pattern.compile("\"token\":.*?\"(.+?)\"", DOTALL);
    private static final Pattern ERROR_PATTERN = Pattern.compile("\"code\":(.+?)" + Pattern.quote("}"), DOTALL);

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

    LoginTokenInterceptor(@NotNull final LoginTokenManager loginTokenManager) {
        this.loginTokenManager = loginTokenManager;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request oldRequest = chain.request();

        if (oldRequest.url().host().equals(ProxerUrls.apiBase().host())) {
            final Request.Builder newRequestBuilder = oldRequest.newBuilder();
            final String loginToken = loginTokenManager.provide();

            if (loginToken != null) {
                newRequestBuilder.addHeader(LOGIN_TOKEN_HEADER, loginToken);
            }

            final Response response = chain.proceed(newRequestBuilder.build());

            if (response.isSuccessful()) {
                final HttpUrl url = response.request().url();
                final Matcher errorMatcher = ERROR_PATTERN.matcher(response.peekBody(MAX_PEEK_BYTE_COUNT).string());
                final Integer errorCode;

                if (errorMatcher.find()) {
                    errorCode = toIntOrNull(errorMatcher.group(1).trim());

                    if (errorCode == null) {
                        return response;
                    }
                } else {
                    errorCode = 0;
                }

                if (errorCode == 0) {
                    if (url.pathSegments().equals(LOGIN_PATH)) {
                        final Matcher matcher = LOGIN_TOKEN_PATTERN.matcher(response.peekBody(MAX_PEEK_BYTE_COUNT)
                                .string());

                        if (matcher.find()) {
                            loginTokenManager.persist(matcher.group(1).trim());
                        }
                    } else if (url.pathSegments().equals(LOGOUT_PATH)) {
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
    private Integer toIntOrNull(@NotNull final String candidate) {
        try {
            return Integer.parseInt(candidate);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private boolean isLoginError(@NotNull final ServerErrorType errorType) {
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
