package com.proxerme.library.connection;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.afollestad.bridge.BridgeException;
import com.proxerme.library.connection.ProxerException.ErrorCode;

import org.json.JSONException;

import static com.proxerme.library.connection.ProxerException.IO;
import static com.proxerme.library.connection.ProxerException.NETWORK;
import static com.proxerme.library.connection.ProxerException.TIMEOUT;
import static com.proxerme.library.connection.ProxerException.UNKNOWN;
import static com.proxerme.library.connection.ProxerException.UNPARSEABLE;

/**
 * A helper class, which converts an Exception to an Integer, represented through the
 * {@link ErrorCode} annotation.
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
    @NonNull
    @CheckResult
    static ProxerException handleException(@SuppressWarnings("UnusedParameters")
                                           @NonNull JSONException jsonException) {
        return new ProxerException(UNPARSEABLE);
    }

    /**
     * Handles a BridgeException and returns the appropriate {@link ProxerException}.
     *
     * @param bridgeException The Exception.
     * @return The appropriate {@link ProxerException}.
     */
    @NonNull
    @CheckResult
    static ProxerException handleException(@NonNull BridgeException bridgeException) {
        switch (bridgeException.reason()) {
            case BridgeException.REASON_REQUEST_TIMEOUT: {
                return new ProxerException(TIMEOUT);
            }

            case BridgeException.REASON_RESPONSE_UNSUCCESSFUL: {
                return new ProxerException(NETWORK);
            }

            case BridgeException.REASON_RESPONSE_UNPARSEABLE: {
                return new ProxerException(UNPARSEABLE);
            }

            case BridgeException.REASON_RESPONSE_IOERROR: {
                return new ProxerException(IO);
            }

            case BridgeException.REASON_RESPONSE_VALIDATOR_FALSE: {
                return new ProxerException(UNKNOWN);
            }

            case BridgeException.REASON_RESPONSE_VALIDATOR_ERROR: {
                //noinspection ConstantConditions
                return (ProxerException) bridgeException.underlyingException();
            }

            case BridgeException.REASON_REQUEST_CANCELLED: {
                return new ProxerException(UNKNOWN);
            }

            case BridgeException.REASON_REQUEST_FAILED: {
                return new ProxerException(NETWORK);
            }

            default: {
                return new ProxerException(UNKNOWN);
            }
        }
    }

}
