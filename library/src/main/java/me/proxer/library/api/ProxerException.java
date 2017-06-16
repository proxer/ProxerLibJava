package me.proxer.library.api;

import com.squareup.moshi.JsonDataException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Common Exception for all errors, occurring around the api.
 * <p>
 * {@link #getErrorType()} returns the general type of error. This could for example be an {@link ErrorType#IO} error,
 * or invalid JSON data (leading to {@link ErrorType#PARSING}). If the type is {@link ErrorType#SERVER},
 * {@link #getServerErrorType()} returns the type of server error. Moreover, message is set in that case.
 *
 * @author Ruben Gees
 */
public final class ProxerException extends Exception {

    /**
     * Returns the error type of this exception.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final ErrorType errorType;

    /**
     * Returns the server error type if, and only if, {@link #getErrorType()} returns {@link ErrorType#SERVER}.
     */
    @Getter(onMethod = @__({@Nullable}))
    private final ServerErrorType serverErrorType;

    /**
     * Returns a error message from the server if, and only if, {@link #getErrorType()} returns {@link ErrorType#SERVER}.
     */
    @Getter(onMethod = @__({@Override, @Nullable}))
    private final String message;

    /**
     * Constructs an instance from the passed {@code error}, {@code serverError}, {@code message} and {@code cause}.
     */
    public ProxerException(@NotNull final ErrorType errorType, @Nullable final ServerErrorType serverErrorType,
                           @Nullable final String message, @Nullable Throwable cause) {
        super(cause);

        this.errorType = errorType;
        this.serverErrorType = serverErrorType;
        this.message = message;
    }

    /**
     * Constructs an instance from the passed {@code error}, {@code serverError} and {@code message}.
     */
    public ProxerException(@NotNull final ErrorType errorType, @Nullable final ServerErrorType serverErrorType,
                           @Nullable final String message) {
        this.errorType = errorType;
        this.serverErrorType = serverErrorType;
        this.message = message;
    }

    /**
     * Constructs an instance from the passed {@code error}, {@code serverErrorCode} and {@code message}.
     * <p>
     * If a invalid number is passed for the {@code serverErrorCode}, an error is thrown.
     */
    public ProxerException(@NotNull final ErrorType errorType, @Nullable final Integer serverErrorCode,
                           @Nullable final String message) {
        this.errorType = errorType;
        this.serverErrorType = ServerErrorType.fromErrorCode(serverErrorCode);
        this.message = message;
    }

    /**
     * Constructs an instance from the passed {@code error} and {@code cause}.
     * <p>
     * {@link #getServerErrorType()} and {@link #getMessage()} will return null.
     */
    public ProxerException(@NotNull final ErrorType errorType, @Nullable Throwable cause) {
        super(cause);

        this.errorType = errorType;
        this.serverErrorType = null;
        this.message = null;
    }

    /**
     * Constructs an instance from the passed {@code error}.
     * <p>
     * {@link #getServerErrorType()} and {@link #getMessage()} will return null.
     */
    public ProxerException(@NotNull final ErrorType errorType) {
        this.errorType = errorType;
        this.serverErrorType = null;
        this.message = null;
    }

    /**
     * Enum containing the available general error types.
     */
    public enum ErrorType {
        SERVER,
        TIMEOUT,
        IO,
        PARSING,
        CANCELLED,
        UNKNOWN
    }

    /**
     * Enum containing the server error types.
     */
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

        private final int code;

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

        static ServerErrorType fromErrorCodeOrNull(@Nullable final Integer errorCode) {
            if (errorCode == null) {
                return null;
            }

            for (final ServerErrorType type : values()) {
                if (type.code == errorCode) {
                    return type;
                }
            }

            return null;
        }
    }
}
