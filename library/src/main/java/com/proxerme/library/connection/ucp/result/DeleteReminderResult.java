package com.proxerme.library.connection.ucp.result;

import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerResult;

/**
 * {@inheritDoc}
 */
public class DeleteReminderResult extends ProxerResult<Void> {

    protected DeleteReminderResult() {
    }

    @Override
    @Nullable
    public Void getData() {
        return null;
    }
}
