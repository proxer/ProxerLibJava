package com.proxerme.library.event.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class SendingMessageFailedEvent extends ErrorEvent {
    public SendingMessageFailedEvent(@NonNull ProxerException exception) {
        super(exception);
    }
}