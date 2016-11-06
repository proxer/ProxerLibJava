package com.proxerme.library.connection.anime;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public abstract class AnimeRequest<T> extends ProxerRequest<T> {

    private static final String CLASS = "anime";

    @NonNull
    @Override
    protected String getApiClass() {
        return CLASS;
    }
}
