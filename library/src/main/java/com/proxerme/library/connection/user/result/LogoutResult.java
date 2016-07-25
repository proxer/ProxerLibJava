package com.proxerme.library.connection.user.result;

import android.support.annotation.Nullable;

import com.proxerme.library.interfaces.ProxerResult;

/**
 * The Result of a {@link com.proxerme.library.connection.user.request.LogoutRequest}. This Result
 * does not have any data and is only there to mark the Request as successful.
 *
 * @author Ruben Gees
 */
public class LogoutResult implements ProxerResult {

    /**
     * Returns null.
     *
     * @return Always null.
     */
    @Nullable
    @Override
    public Object getItem() {
        return null;
    }
}
