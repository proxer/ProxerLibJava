package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.proxerme.library.result.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class MessageSentResult implements ProxerResult {

    private String conferenceId;

    public MessageSentResult(@NonNull String conferenceId) {
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
