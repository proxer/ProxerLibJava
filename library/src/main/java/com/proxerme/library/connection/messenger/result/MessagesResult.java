package com.proxerme.library.connection.messenger.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.Message;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * Result of a {@link com.proxerme.library.connection.messenger.request.MessagesRequest}.
 *
 * @author Ruben Gees
 */

public class MessagesResult implements ProxerResult<Message[]> {

    @Body(name = "data")
    Message[] item;

    MessagesResult() {
    }

    /**
     * The constructor.
     *
     * @param item The array of retrieved messages.
     */
    public MessagesResult(@NonNull Message[] item) {
        this.item = item;
    }

    /**
     * Returns the array of retrieved messages.
     * @return The array of messages.
     */
    @Override
    @NonNull
    public Message[] getItem() {
        return item;
    }
}
