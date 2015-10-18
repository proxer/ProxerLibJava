package com.proxerme.library.connection;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.proxerme.library.connection.ProxerException.ErrorCodes.IO;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.NETWORK;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.PROXER;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.TIMEOUT;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.UNKNOWN;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.UNPARSEABLE;

/**
 * A subclass of {@link Exception}, representing all possible connection problems.
 *
 * @author Ruben Gees
 */
public class ProxerException extends Exception {

    @ErrorCode
    private int errorCode;

    public ProxerException(@ErrorCode int errorCode) {
        this.errorCode = errorCode;
    }

    public ProxerException(@ErrorCode int errorCode, @Nullable String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
    }

    @ErrorCode
    public int getErrorCode() {
        return errorCode;
    }

    @IntDef({PROXER, NETWORK, UNPARSEABLE, IO,
            TIMEOUT, UNKNOWN})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ErrorCode {
    }

    public class ErrorCodes {
        public static final int PROXER = 0;
        public static final int NETWORK = 1;
        public static final int UNPARSEABLE = 2;
        public static final int IO = 3;
        public static final int TIMEOUT = 4;
        public static final int UNKNOWN = 5;
    }
}
