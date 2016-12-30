package com.proxerme.library.connection.ucp.result;

import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerResult;

/**
 * {@inheritDoc}
 */
public class DeleteFavoriteResult extends ProxerResult<Void> {

    protected DeleteFavoriteResult() {
    }

    @Override
    @Nullable
    public Void getData() {
        return null;
    }
}
