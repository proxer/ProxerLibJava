package com.proxerme.library.api;

import com.squareup.moshi.JsonDataException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class ProxerException extends Exception {

    private ErrorType error;
    private ServerErrorType serverError;
    private String message;

    public ProxerException(@NotNull final ErrorType error, @Nullable final ServerErrorType serverError,
                           @Nullable final String message) {
        this.error = error;
        this.serverError = serverError;
        this.message = message;
    }

    public ProxerException(@NotNull final ErrorType error, @Nullable final Integer serverErrorCode,
                           @Nullable final String message) {
        this.error = error;
        this.serverError = ServerErrorType.fromErrorCode(serverErrorCode);
        this.message = message;
    }

    public ProxerException(@NotNull final ErrorType error) {
        this.error = error;
    }

    @NotNull
    public ErrorType getError() {
        return error;
    }

    @Nullable
    public ServerErrorType getServerError() {
        return serverError;
    }

    @Override
    @Nullable
    public String getMessage() {
        return message;
    }

    public enum ErrorType {
        SERVER,
        TIMEOUT,
        IO,
        PARSING,
        UNKNOWN
    }

    public enum ServerErrorType {
        UNKNOWN_API(1000),
        API_REMOVED(1001),
        INVALID_API_CLASS(1002),
        INVALID_API_FUNCTION(1003),
        INSUFFICIENT_PERMISSIONS(1004),
        INVALID_TOKEN(1005),
        FUNCTION_BLOCKED(1006),
        SERVER_MAINTENANCE(1007),
        API_MAINTENANCE(1008),

        IP_BLOCKED(2000),
        NEWS(2001),

        LOGIN_MISSING_CREDENTIALS(3000),
        LOGIN_INVALID_CREDENTIALS(3001),
        NOTIFICATIONS_LOGIN_REQUIRED(3002),
        USERINFO_INVALID_ID(3003),
        UCP_LOGIN_REQUIRED(3004),
        UCP_INVALID_CATEGORY(3005),
        UCP_INVALID_ID(3006),
        INFO_INVALID_ID(3007),
        INFO_INVALID_TYPE(3008),
        INFO_LOGIN_REQUIRED(3009),
        INFO_ENTRY_ALREADY_IN_LIST(3010),
        INFO_EXCEEDED_MAXIMUM_ENTRIES(3011),
        LOGIN_ALREADY_LOGGED_IN(3012),
        LOGIN_DIFFERENT_USER_ALREADY_LOGGED_IN(3013),
        USER_INSUFFICIENT_PERMISSIONS(3014),
        LIST_INVALID_CATEGORY(3015),
        LIST_INVALID_MEDIUM(3016),
        MEDIA_INVALID_STYLE(3017),
        MEDIA_INVALID_ENTRY(3018),
        MANGA_INVALID_CHAPTER(3019),
        ANIME_INVALID_EPISODE(3020),
        ANIME_INVALID_STREAM(3021),
        UCP_INVALID_EPISODE(3022),
        MESSAGES_LOGIN_REQUIRED(3023),
        MESSAGES_INVALID_CONFERENCE(3024),
        MESSAGES_INVALID_REPORT_INPUT(3025),
        MESSAGES_INVALID_MESSAGE(3026),
        MESSAGES_INVALID_USER(3027),
        MESSAGES_EXCEEDED_MAXIMUM_USERS(3028),
        MESSAGES_INVALID_TOPIC(3029),
        MESSAGES_MISSING_USER(3030),
        CHAT_INVALID_ROOM(3031),
        CHAT_INVALID_PERMISSIONS(3032),
        CHAT_INVALID_MESSAGE(3033),
        CHAT_LOGIN_REQUIRED(3034),
        LIST_INVALID_LANGUAGE(3035),
        LIST_INVALID_TYPE(3036),
        LIST_INVALID_ID(3037),
        USER_2FA_SECRET_REQUIRED(3038),
        USER_ACCOUNT_EXPIRED(3039),
        USER_ACCOUNT_BLOCKED(3040),
        USER(3041),
        ERRORLOG_INVALID_INPUT(3042);

        private int code;

        ServerErrorType(final int code) {
            this.code = code;
        }

        static ServerErrorType fromErrorCode(@Nullable final Integer errorCode) {
            if (errorCode == null) {
                return null;
            }

            for (final ServerErrorType type : values()) {
                if (type.code == errorCode) {
                    return type;
                }
            }

            throw new JsonDataException("No value found for the code: " + errorCode);
        }
    }
}
