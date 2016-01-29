package com.proxerme.library.event.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public abstract class ErrorEvent {

    private ProxerException exception;

    public ErrorEvent(@NonNull ProxerException exception) {
        this.exception = exception;
    }

    @NonNull
    public ProxerException getException() {
        return exception;
    }
}
