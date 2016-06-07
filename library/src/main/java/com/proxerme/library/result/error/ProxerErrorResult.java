package com.proxerme.library.result.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.result.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public abstract class ProxerErrorResult implements ProxerResult<ProxerException> {

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
