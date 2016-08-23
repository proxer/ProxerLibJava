package com.proxerme.library.connection.info;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;

/**
 * Base request for all requests in the "info" API class.
 *
 * @author Desnoo
 */
public abstract class InfoRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "info";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
