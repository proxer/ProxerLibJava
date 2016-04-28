package com.proxerme.library.event.success;

import android.support.annotation.NonNull;

import com.proxerme.library.event.IEvent;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessageSentEvent implements IEvent {

    private String conferenceId;

    public MessageSentEvent(@NonNull String conferenceId) {
        this.conferenceId = conferenceId;
    }

    @NonNull
    @Override
    public Object getItem() {
        //noinspection ConstantConditions
        return null;
    }

    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }
}
