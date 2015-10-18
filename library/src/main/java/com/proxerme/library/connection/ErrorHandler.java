package com.proxerme.library.connection;

import android.support.annotation.NonNull;

import com.afollestad.bridge.BridgeException;

import org.json.JSONException;

import static com.proxerme.library.connection.ProxerException.ErrorCode;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.IO;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.NETWORK;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.PROXER;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.TIMEOUT;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.UNKNOWN;
import static com.proxerme.library.connection.ProxerException.ErrorCodes.UNPARSEABLE;

/**
 * A Helper class, which converts an {@link Exception} to a integer, represented through the
 * Annotation {@link ErrorCode}. It also has
 * a Method to convert a ErrorCode into a human readable {@link String}.
 *
 * @author Ruben Gees
 */
class ErrorHandler {

    @NonNull
    public static ProxerException handleException(@NonNull JSONException jsonException) {
        return new ProxerException(UNPARSEABLE);
    }

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

}
