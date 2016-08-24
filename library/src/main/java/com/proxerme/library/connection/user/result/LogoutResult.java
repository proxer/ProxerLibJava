package com.proxerme.library.connection.user.result;

import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerResult;

/**
 * {@inheritDoc}
 */
public final class LogoutResult extends ProxerResult<Void> {

    protected LogoutResult() {
    }

    @Override
    @Nullable
    public Void getData() {
        return null;
    }
}
