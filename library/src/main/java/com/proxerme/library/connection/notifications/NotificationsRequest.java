package com.proxerme.library.connection.notifications;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public abstract class NotificationsRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "notifications";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
