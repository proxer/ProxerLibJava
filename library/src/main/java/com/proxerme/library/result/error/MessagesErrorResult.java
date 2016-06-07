package com.proxerme.library.result.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessagesErrorResult extends ProxerErrorResult {

    private String conferenceId;

    public MessagesErrorResult(@NonNull String conferenceId, @NonNull ProxerException exception) {
        super(exception);

        this.conferenceId = conferenceId;
    }

    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }
}
