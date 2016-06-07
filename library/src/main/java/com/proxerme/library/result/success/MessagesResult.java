package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.Message;
import com.proxerme.library.result.ProxerListResult;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessagesResult implements ProxerListResult<Message> {

    private String conferenceId;
    private List<Message> messages;

    public MessagesResult(@NonNull String conferenceId, @NonNull List<Message> messages) {
        this.conferenceId = conferenceId;
        this.messages = messages;
    }

    @NonNull
    @Override
    public List<Message> getItem() {
        return messages;
    }

    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }
}
