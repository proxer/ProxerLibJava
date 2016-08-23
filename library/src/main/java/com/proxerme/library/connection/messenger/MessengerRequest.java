package com.proxerme.library.connection.messenger;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;

/**
 * Base request for all requests in the "messenger" API class.
 *
 * @author Ruben Gees
 */
public abstract class MessengerRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "messenger";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
