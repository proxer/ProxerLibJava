package com.proxerme.library.event.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.Message;
import com.proxerme.library.event.IListEvent;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessagesEvent implements IListEvent<Message> {

    private String conferenceId;
    private List<Message> messages;

    public MessagesEvent(@NonNull String conferenceId, @NonNull List<Message> messages) {
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
