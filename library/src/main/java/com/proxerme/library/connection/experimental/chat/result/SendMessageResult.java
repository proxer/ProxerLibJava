package com.proxerme.library.connection.experimental.chat.result;

import android.support.annotation.Nullable;

import com.proxerme.library.interfaces.ProxerResult;

/**
 * The result of a
 * {@link com.proxerme.library.connection.experimental.chat.request.SendMessageRequest}. This Result
 * does not have any data and is only there to mark the Request as successful.
 *
 * @author Ruben Gees
 */
public class SendMessageResult implements ProxerResult {

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
