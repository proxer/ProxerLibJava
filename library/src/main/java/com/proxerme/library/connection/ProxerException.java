package com.proxerme.library.connection;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An Exception for representation of all possible connection and server errors.
 *
 * @author Ruben Gees
 */
public class ProxerException extends Exception {

    /**
     * An error occurred on the server (Wrong user input, blocked IP, ...).
     */
    public static final int PROXER = 0;

    /**
     * The server didn't answer in time.
     */
    public static final int TIMEOUT = 1;

    /**
     * The network is not available.
     */
    public static final int NETWORK = 2;

    /**
     * The result was not parsable (The server sent bad data).
     */
    public static final int UNPARSABLE = 3;

    /**
     * The request has been cancelled.
     */
    public static final int CANCELLED = 4;

    public static final int UNKNOWN_API = 1000;
    public static final int API_REMOVED = 1001;
    public static final int UNKNOWN_API_CLASS = 1002;
    public static final int UNKNOWN_API_FUNCTION = 1003;
    public static final int INSUFFICIENT_RIGHTS = 1004;
    public static final int INVALID_TOKEN = 1005;
    public static final int FUNCTION_BLOCKED = 1006;

    public static final int IP_BLOCKED = 2000;
    public static final int NEWS = 2001;

    public static final int LOGIN_MISSING_CREDENTIALS = 3000;
    public static final int LOGIN_INVALID_CREDENTIALS = 3001;
    public static final int NOTIFICATIONS_USER_NOT_LOGGED_IN = 3002;
    public static final int USERINFO_INVALID_ID = 3003;
    public static final int UCP_USER_NOT_LOGGED_IN = 3004;
    public static final int UCP_INVALID_CATEGORY = 3005;
    public static final int UCP_INVALID_ID = 3006;
    public static final int INFO_INVALID_ID = 3007;
    public static final int INFO_INVALID_TYPE = 3008;
    public static final int INFO_USER_NOT_LOGGED_IN = 3009;
    public static final int INFO_ENTRY_ALREADY_IN_LIST = 3010;
    public static final int INFO_EXCEEDED_ALLOWED_ENTRIES = 3011;
    public static final int LOGIN_ALREADY_LOGGED_IN = 3012;
    public static final int LOGIN_DIFFERENT_USER_ALREADY_LOGGED_IN = 3013;
    public static final int USER_ACCESS_DENIED = 3014;
    public static final int LIST_UNKNOWN_CATEGORY = 3015;
    public static final int LIST_UNKNOWN_MEDIUM = 3016;
    public static final int MEDIA_UNKNOWN_STYLE = 3017;
    public static final int MEDIA_UNKNOWN_ENTRY = 3018;
    public static final int MANGA_UNKNOWN_CHAPTER = 3019;
    public static final int ANIME_UNKNOWN_EPISODE = 3020;
    public static final int ANIME_UNKNOWN_STREAM = 3021;
    public static final int UCP_UNKNOWN_EPISODE = 3022;
    public static final int MESSAGES_USER_NOT_LOGGED_IN = 3023;
    public static final int MESSAGES_INVALID_CONFERENCE = 3024;
    public static final int MESSAGES_MISSING_REPORT_INPUT = 3025;
    public static final int MESSAGES_INVALID_MESSAGE = 3026;
    public static final int MESSAGES_INVALID_USER = 3027;
    public static final int MESSAGES_MAXIMUM_USERS_EXCEEDED = 3028;
    public static final int MESSAGES_INVALID_TOPIC = 3029;
    public static final int MESSAGES_MISSING_USER = 3030;

    @ErrorCode
    private int errorCode;
    @ProxerErrorCode
    private Integer proxerErrorCode;

    /**
     * @param errorCode The errorCode. This is one of the through {@link ErrorCode} specified codes.
     */
    public ProxerException(@ErrorCode int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param errorCode     The errorCode. This is one of the through {@link ErrorCode} specified
     *                      codes.
     * @param detailMessage A message containing additional info.
     */
    public ProxerException(@ErrorCode int errorCode, @Nullable String detailMessage) {
        super(detailMessage);

        this.errorCode = errorCode;
    }

    /**
     * @param errorCode       The error code. If a code is passed to the proxerErrorCode parameter,
     *                        this has to be {@link ProxerException#PROXER}.
     * @param detailMessage   A message containing additional info.
     * @param proxerErrorCode The proxer error code. If the error was a server error, one of the
     *                        {@link ProxerErrorCode} should be passed.
     */
    public ProxerException(@ErrorCode int errorCode, @Nullable String detailMessage,
                           @Nullable Integer proxerErrorCode) {
        super(detailMessage);

        this.errorCode = errorCode;
        this.proxerErrorCode = proxerErrorCode;
    }

    /**
     * Returns the {@link ErrorCode} of this Exception.
     *
     * @return The code.
     */
    @ErrorCode
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Returns the {@link ProxerErrorCode} of this Exception, if it is a server error.
     *
     * @return The code.
     */
    @Nullable
    @ProxerErrorCode
    public Integer getProxerErrorCode() {
        return proxerErrorCode;
    }

    /**
     * An annotation representing the different error codes which might occur.
     */
    @IntDef({PROXER, NETWORK, TIMEOUT, UNPARSABLE, CANCELLED})
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ErrorCode {
    }

    /**
     * An annotation representing the different server error codes which might occur.
     */
    @IntDef({UNKNOWN_API, API_REMOVED, UNKNOWN_API_CLASS, UNKNOWN_API_FUNCTION, INSUFFICIENT_RIGHTS,
            INVALID_TOKEN, FUNCTION_BLOCKED, IP_BLOCKED, NEWS, LOGIN_MISSING_CREDENTIALS,
            LOGIN_INVALID_CREDENTIALS, NOTIFICATIONS_USER_NOT_LOGGED_IN, USERINFO_INVALID_ID,
            UCP_USER_NOT_LOGGED_IN, UCP_INVALID_CATEGORY, UCP_INVALID_ID, INFO_INVALID_ID,
            INFO_INVALID_TYPE, INFO_USER_NOT_LOGGED_IN, INFO_ENTRY_ALREADY_IN_LIST,
            INFO_EXCEEDED_ALLOWED_ENTRIES, LOGIN_ALREADY_LOGGED_IN,
            LOGIN_DIFFERENT_USER_ALREADY_LOGGED_IN, USER_ACCESS_DENIED, LIST_UNKNOWN_CATEGORY,
            LIST_UNKNOWN_MEDIUM, MEDIA_UNKNOWN_STYLE, MEDIA_UNKNOWN_ENTRY, MANGA_UNKNOWN_CHAPTER,
            ANIME_UNKNOWN_EPISODE, ANIME_UNKNOWN_STREAM, UCP_UNKNOWN_EPISODE,
            MESSAGES_USER_NOT_LOGGED_IN, MESSAGES_INVALID_CONFERENCE, MESSAGES_MISSING_REPORT_INPUT,
            MESSAGES_INVALID_MESSAGE, MESSAGES_INVALID_USER, MESSAGES_MAXIMUM_USERS_EXCEEDED,
            MESSAGES_INVALID_TOPIC, MESSAGES_MISSING_USER})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ProxerErrorCode {
    }
}
