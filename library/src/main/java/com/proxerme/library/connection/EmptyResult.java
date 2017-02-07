package com.proxerme.library.connection;

import android.support.annotation.Nullable;

/**
 * Empty result for APIs that don't return anything, like the
 * {@link com.proxerme.library.connection.ucp.request.DeleteReminderRequest}.
 *
 * @author Ruben Gees
 */
public class EmptyResult extends ProxerResult<Void> {

    protected EmptyResult() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nullable
    public Void getData() {
        return null;
    }
}
