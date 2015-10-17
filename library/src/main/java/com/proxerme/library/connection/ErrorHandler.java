package com.proxerme.library.connection;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
    public static ProxerException handleException(@NonNull JSONException jsonException) {
        return new ProxerException(UNPARSEABLE);
    }

    @NonNull
    public static ProxerException handleException(@NonNull VolleyError error) {
        ProxerException exception;

        if (error instanceof ParseError) {
            exception = new ProxerException(UNPARSEABLE);
        } else if (error instanceof TimeoutError) {
            exception = new ProxerException(TIMEOUT);
        } else if (error instanceof NetworkError) {
            exception = new ProxerException(NETWORK);
        } else {
            exception = new ProxerException(UNKNOWN);
        }

        return exception;
    }

    @IntDef({PROXER, NETWORK, UNPARSEABLE, TIMEOUT, UNKNOWN})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface ErrorCode {
    }

    public class ErrorCodes {
        public static final int PROXER = 0;
        public static final int NETWORK = 1;
        public static final int UNPARSEABLE = 2;
        public static final int TIMEOUT = 3;
        public static final int UNKNOWN = 4;
    }

}
