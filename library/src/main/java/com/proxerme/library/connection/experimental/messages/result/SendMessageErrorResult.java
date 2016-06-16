package com.proxerme.library.connection.experimental.messages.result;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.interfaces.ProxerErrorResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class SendMessageErrorResult extends ProxerErrorResult {

    private String conferenceId;

    public SendMessageErrorResult(@NonNull String conferenceId,
                                  @NonNull ProxerException exception) {
        super(exception);

        this.conferenceId = conferenceId;
    }

    @NonNull
    public String getConferenceId() {
        return conferenceId;
    }
}
