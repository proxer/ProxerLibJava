package com.proxerme.library.connection;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A subclass of {@link Exception}, representing all possible connection problems.
 *
 * @author Ruben Gees
 */
public class ProxerException extends Exception {

    /**
     * An error happened on the server itself or entered user data is incorrect.
     */
    public static final int ERROR_PROXER = 0;

    /**
     * An error with the network, like no connectivity or wrong configuration.
     */
    public static final int ERROR_NETWORK = 1;

    /**
     * The result of a request was not in the expected format.
     */
    public static final int ERROR_UNPARSEABLE = 2;

    /**
     * There were errors when trying to access storage.
     */
    public static final int ERROR_IO = 3;

    /**
     * The connection timed out due to connectivity or server problems.
     */
    public static final int ERROR_TIMEOUT = 4;

    /**
     * Every other error, which doesn't fit into one of the other categories.
     */
    public static final int ERROR_UNKNOWN = 5;

    @ErrorCode
    private int errorCode;

    public ProxerException(@ErrorCode int errorCode) {
        this.errorCode = errorCode;
    }

    public ProxerException(@ErrorCode int errorCode, @Nullable String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
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
     * An annotation, representing the different error codes, which might occur.
     */
    @IntDef({ERROR_PROXER, ERROR_NETWORK, ERROR_UNPARSEABLE, ERROR_IO, ERROR_TIMEOUT,
            ERROR_UNKNOWN})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ErrorCode {
    }
}
