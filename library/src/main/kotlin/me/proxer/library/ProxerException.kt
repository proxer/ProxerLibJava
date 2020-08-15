package me.proxer.library

import me.proxer.library.ProxerException.ErrorType

/**
 * Common Exception for all errors, occurring around the api.
 *
 * [errorType] returns the general type of error. This could for example be an [ErrorType.IO] error,
 * or invalid JSON data (leading to [ErrorType.PARSING]). If the type is [ErrorType.SERVER],
 * [serverErrorType] returns the type of server error. Moreover, message is set in that case.
 *
 * @author Ruben Gees
 */
class ProxerException : Exception {

    /**
     * Returns the error type of this exception.
     */
    val errorType: ErrorType

    /**
     * Returns the server error type if, and only if, [errorType] returns [ErrorType.SERVER].
     */
    val serverErrorType: ServerErrorType?

    /**
     * Constructs an instance from the passed [error], [serverErrorType], [message] and [cause].
     */
    @JvmOverloads
    constructor(
        errorType: ErrorType,
        serverErrorType: ServerErrorType? = null,
        message: String? = null,
        cause: Exception? = null
    ) : super(message, cause) {
        this.errorType = errorType
        this.serverErrorType = serverErrorType
    }

    /**
     * Constructs an instance from the passed [error], [serverErrorCode] and [message].
     *
     * If a invalid number is passed for the [serverErrorCode], an error is thrown.
     */
    constructor(errorType: ErrorType, serverErrorCode: Int?, message: String?) : super(message) {
        this.errorType = errorType
        this.serverErrorType = ServerErrorType.fromErrorCode(serverErrorCode)
    }

    /**
     * Enum containing the available general error types.
     */
    enum class ErrorType {
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
    enum class ServerErrorType(val code: Int) {
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
        ERRORLOG_INVALID_INPUT(3042),
        LIST_INVALID_SUBJECT(3043),
        FORUM_INVALID_ID(3044),
        APPS_INVALID_ID(3045),
        LIST_TOP_ACCESS_RESET(3046),
        AUTH_INVALID_USER(3047),
        AUTH_INVALID_CODE(3048),
        AUTH_CODE_ALREADY_EXISTS(3049),
        AUTH_CODE_DOES_NOT_EXIST(3050),
        AUTH_CODE_REJECTED(3051),
        AUTH_CODE_PENDING(3052),
        AUTH_CODE_INVALID_NAME(3053),
        AUTH_CODE_DUPLICATE(3054),
        CHAT_SEVEN_DAY_PROTECTION(3055),
        CHAT_USER_ON_BLACKLIST(3056),
        CHAT_NO_PERMISSIONS(3057),
        CHAT_INVALID_THANK_YOU(3058),
        CHAT_INVALID_INPUT(3059),
        FORUM_INVALID_PERMISSIONS(3060),
        INFO_DELETE_COMMENT_INVALID_INPUT(3061),
        UCP_INVALID_SETTINGS(3062),
        ANIME_LOGIN_REQUIRED(3063),
        IP_AUTHENTICATION_REQUIRED(3064),
        MEDIA_NO_VAST_TAG(3065),
        LIST_NO_ENTRIES_LEFT(3066),
        COMMENT_LOGIN_REQUIRED(3067),
        COMMENT_INVALID_ID(3068),
        COMMENT_INVALID_COMMENT(3069),
        COMMENT_INSUFFICIENT_PERMISSIONS(3070),
        COMMENT_INVALID_RATING(3071),
        COMMENT_INVALID_EPISODE(3072),
        COMMENT_NOT_ACTIVE_YET(3073),
        COMMENT_INVALID_STATUS(3074),
        COMMENT_SAVE_ERROR(3075),
        COMMENT_INVALID_ENTRY_ID(3076),
        COMMENT_INVALID_CONTENT(3077),
        COMMENT_ALREADY_EXISTS(3078),

        UNKNOWN(10_000),
        RATE_LIMIT(99_998),
        INTERNAL(99_999);

        companion object {
            internal fun fromErrorCode(errorCode: Int?): ServerErrorType {
                return enumValues<ServerErrorType>().find { it.code == errorCode } ?: UNKNOWN
            }
        }

        internal val isLoginError
            get() = when (this) {
                INVALID_TOKEN,
                NOTIFICATIONS_LOGIN_REQUIRED,
                UCP_LOGIN_REQUIRED,
                INFO_LOGIN_REQUIRED,
                LOGIN_ALREADY_LOGGED_IN,
                LOGIN_DIFFERENT_USER_ALREADY_LOGGED_IN,
                MESSAGES_LOGIN_REQUIRED,
                CHAT_LOGIN_REQUIRED,
                USER_2FA_SECRET_REQUIRED,
                ANIME_LOGIN_REQUIRED,
                IP_AUTHENTICATION_REQUIRED,
                COMMENT_LOGIN_REQUIRED -> true

                else -> false
            }
    }
}
