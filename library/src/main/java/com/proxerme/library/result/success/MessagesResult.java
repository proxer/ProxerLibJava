package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.Message;
import com.proxerme.library.result.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessagesResult implements ProxerResult<Message[]> {

    private String conferenceId;
    private Message[] messages;

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
