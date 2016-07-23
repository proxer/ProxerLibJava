package com.proxerme.library.interfaces;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class ProxerErrorResult implements ProxerResult<ProxerException> {

    private ProxerException exception;

    public ProxerErrorResult(@NonNull ProxerException exception) {
        this.exception = exception;
    }

    @NonNull
    @Override
    public final ProxerException getItem() {
        return exception;
    }
}
