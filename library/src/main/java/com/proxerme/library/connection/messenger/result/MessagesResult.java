package com.proxerme.library.connection.messenger.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.Message;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class MessagesResult implements ProxerResult<Message[]> {

    @Body(name = "data")
    Message[] item;

    MessagesResult() {
    }

    public MessagesResult(@NonNull Message[] item) {
        this.item = item;
    }

    @Override
    @NonNull
    public Message[] getItem() {
        return item;
    }
}
