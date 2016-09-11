package com.proxerme.library.connection.ucp;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;

/**
 * Base request for all requests in the "ucp" API class.
 *
 * @author Ruben Gees
 */
public abstract class UcpRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "ucp";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
