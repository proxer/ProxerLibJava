package com.proxerme.library.connection.user;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;

/**
 * Base request for all requests in the "user" API class.
 *
 * @author Ruben Gees
 */
public abstract class UserRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "user";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
