package com.proxerme.library.connection.list;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;

/**
 * Base request for all requests in the "list" API class.
 *
 * @author Ruben Gees
 */
public abstract class ListRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "list";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
