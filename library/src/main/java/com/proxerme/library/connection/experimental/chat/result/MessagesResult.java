package com.proxerme.library.connection.experimental.chat.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.experimental.chat.entity.Message;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessagesResult implements ProxerResult<Message[]> {

    String conferenceId;
    @Body(name = "messages")
    Message[] messages;

    MessagesResult() {

    }

    public MessagesResult(@NonNull String conferenceId, @NonNull Message[] messages) {
        this.conferenceId = conferenceId;
        this.messages = messages;
    }

    @NonNull
    @Override
    public Message[] getItem() {
        return messages;
    }

    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }
}
