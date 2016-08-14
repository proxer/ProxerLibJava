package com.proxerme.library.connection.messenger.result;

import android.support.annotation.Nullable;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * Result of a {@link com.proxerme.library.connection.messenger.request.SendMessageRequest}.
 *
 * @author Ruben Gees
 */

public class SendMessageResult implements ProxerResult<String> {

    @Nullable
    @Body(name = "data")
    String item;

    SendMessageResult() {
    }

    /**
     * The constructor.
     *
     * @param item The status message. Null if no error occurred.
     */
    public SendMessageResult(@Nullable String item) {
        this.item = item;
    }

    /**
     * Returns the status message of the Request. This will be null if no error occurred. The client
     * should always check this value.
     *
     * @return The status message or null if no error occurred.
     */
    @Override
    @Nullable
    public String getItem() {
        return item;
    }
}
