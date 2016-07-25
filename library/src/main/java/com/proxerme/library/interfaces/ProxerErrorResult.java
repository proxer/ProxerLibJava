package com.proxerme.library.interfaces;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * The Result passed by {@link com.proxerme.library.connection.ProxerRequest} in case an error
 * occurred.
 *
 * @author Ruben Gees
 */
public class ProxerErrorResult implements ProxerResult<ProxerException> {

    private ProxerException exception;

    /**
     * The constructor.
     *
     * @param exception The Exception.
     */
    public ProxerErrorResult(@NonNull ProxerException exception) {
        this.exception = exception;
    }

    /**
     * Returns the Exception.
     *
     * @return The Exception.
     */
    @NonNull
    @Override
    public final ProxerException getItem() {
        return exception;
    }
}
