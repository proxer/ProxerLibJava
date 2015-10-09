package com.proxerme.library.connection;

import android.support.annotation.Nullable;

import static com.proxerme.library.connection.ErrorHandler.ErrorCode;

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
}
