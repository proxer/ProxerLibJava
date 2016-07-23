package com.proxerme.library.connection.experimental.chat.result;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class SendMessageResult implements ProxerResult {

    private String conferenceId;

    public SendMessageResult(@NonNull String conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Nullable
    @Override
    public Object getItem() {
        return null;
    }

    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }
}
