package com.proxerme.library.event.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessagesErrorEvent extends ErrorEvent {

    private String conferenceId;

    public MessagesErrorEvent(@NonNull String conferenceId, @NonNull ProxerException exception) {
        super(exception);

        this.conferenceId = conferenceId;
    }

    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }
}
