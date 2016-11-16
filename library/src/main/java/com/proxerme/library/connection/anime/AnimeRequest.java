package com.proxerme.library.connection.anime;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;

/**
 * Base request for all requests in the "anime" API class.
 *
 * @author Desnoo
 */
public abstract class AnimeRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "anime";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
