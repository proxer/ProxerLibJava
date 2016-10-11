package com.proxerme.library.connection.manga;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;


/**
 * Base request for all requests in the "manga" API class.
 *
 * @author Ruben Gees
 */
public abstract class MangaRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "manga";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
