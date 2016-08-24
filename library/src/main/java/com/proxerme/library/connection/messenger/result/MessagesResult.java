package com.proxerme.library.connection.messenger.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.entity.Message;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class MessagesResult extends ProxerResult<Message[]> {

    @Json(name = "data")
    private Message[] messages;

    protected MessagesResult() {
    }

    @Override
    public Message[] getData() {
        return messages;
    }
}
