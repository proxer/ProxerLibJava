package com.proxerme.library.event.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.event.IEvent;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public abstract class ErrorEvent implements IEvent<ProxerException> {

    private ProxerException exception;

    public ErrorEvent(@NonNull ProxerException exception) {
        this.exception = exception;
    }

    @NonNull
    @Override
    public final ProxerException getItem() {
        return exception;
    }
}
