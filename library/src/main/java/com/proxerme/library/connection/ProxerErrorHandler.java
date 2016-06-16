package com.proxerme.library.connection;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.afollestad.bridge.BridgeException;
import com.proxerme.library.connection.ProxerException.ErrorCode;

import org.json.JSONException;

import static com.proxerme.library.connection.ProxerException.ERROR_IO;
import static com.proxerme.library.connection.ProxerException.ERROR_NETWORK;
import static com.proxerme.library.connection.ProxerException.ERROR_PROXER;
import static com.proxerme.library.connection.ProxerException.ERROR_TIMEOUT;
import static com.proxerme.library.connection.ProxerException.ERROR_UNKNOWN;
import static com.proxerme.library.connection.ProxerException.ERROR_UNPARSEABLE;

/**
 * A Helper class, which converts an Exception to a Integer, represented through the
 * {@link ErrorCode} annotation. It also has
 * a method to convert an ErrorCode into a human readable String.
 *
 * @author Ruben Gees
 */
class ProxerErrorHandler {

    /**
     * Handles a JSONException and returns the appropriate {@link ProxerException}.
     *
     * @param jsonException The Exception.
     * @return The appropriate {@link ProxerException}.
     */
    @SuppressWarnings("UnusedParameters")
    @NonNull
    @CheckResult
    public static ProxerException handleException(@NonNull JSONException jsonException) {
        return new ProxerException(ERROR_UNPARSEABLE);
    }

    /**
     * Handles a BridgeException and returns the appropriate {@link ProxerException}.
     * @param bridgeException The Exception.
     * @return The appropriate {@link ProxerException}.
     */
    @NonNull
    @CheckResult
    public static ProxerException handleException(@NonNull BridgeException bridgeException) {
        ProxerException exception;

        switch (bridgeException.reason()) {
            case BridgeException.REASON_REQUEST_TIMEOUT: {
                exception = new ProxerException(ERROR_TIMEOUT);
                break;
            }
            case BridgeException.REASON_RESPONSE_UNSUCCESSFUL: {
                exception = new ProxerException(ERROR_NETWORK);
                break;
            }
            case BridgeException.REASON_RESPONSE_UNPARSEABLE: {
                exception = new ProxerException(ERROR_UNPARSEABLE);
                break;
            }
            case BridgeException.REASON_RESPONSE_IOERROR: {
                exception = new ProxerException(ERROR_IO);
                break;
            }
            case BridgeException.REASON_RESPONSE_VALIDATOR_FALSE:
                exception = new ProxerException(ERROR_UNKNOWN);
                break;
            case BridgeException.REASON_RESPONSE_VALIDATOR_ERROR:
                exception = new ProxerException(ERROR_PROXER,
                        bridgeException.getMessage());
                break;
            case BridgeException.REASON_REQUEST_CANCELLED:
                exception = new ProxerException(ERROR_UNKNOWN);
                break;
            case BridgeException.REASON_REQUEST_FAILED:
                exception = new ProxerException(ERROR_NETWORK);
                break;
            default:
                exception = new ProxerException(ERROR_UNKNOWN);
                break;
        }

        return exception;
    }

}
