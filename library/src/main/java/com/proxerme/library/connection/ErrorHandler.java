package com.proxerme.library.connection;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.afollestad.bridge.BridgeException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.IO;
import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.NETWORK;
import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.PROXER;
import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.TIMEOUT;
import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.UNKNOWN;
import static com.proxerme.library.connection.ErrorHandler.ErrorCodes.UNPARSEABLE;

/**
 * A Helper class, which converts an {@link Exception} to a integer, represented through the
 * Annotation {@link ErrorCode}. It also has
 * a Method to convert a ErrorCode into a human readable {@link String}.
 *
 * @author Ruben Gees
 */
public class ErrorHandler {

    @NonNull
    public static ProxerException handleException(@NonNull BridgeException bridgeException) {
        ProxerException exception;

        switch (bridgeException.reason()) {
            case BridgeException.REASON_REQUEST_TIMEOUT: {
                exception = new ProxerException(TIMEOUT);
                break;
            }
            case BridgeException.REASON_RESPONSE_UNSUCCESSFUL: {
                exception = new ProxerException(NETWORK);
                break;
            }
            case BridgeException.REASON_RESPONSE_UNPARSEABLE: {
                exception = new ProxerException(UNPARSEABLE);
                break;
            }
            case BridgeException.REASON_RESPONSE_IOERROR: {
                exception = new ProxerException(IO);
                break;
            }
            case BridgeException.REASON_RESPONSE_VALIDATOR_FALSE:
                exception = new ProxerException(UNKNOWN);
                break;
            case BridgeException.REASON_RESPONSE_VALIDATOR_ERROR:
                exception = new ProxerException(PROXER, bridgeException.getMessage());
                break;
            default:
                exception = new ProxerException(UNKNOWN);
                break;
        }

        return exception;
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
