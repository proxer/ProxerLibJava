package com.proxerme.library.connection.messenger.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.entity.Message;

public final class MessagesResult extends ProxerResult<Message[]> {

    private Message[] messages;

    private MessagesResult() {
    }

    @Override
    public Message[] getData() {
        return messages;
    }
}
