package com.proxerme.library.event.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * Created by ruben on 30.03.16.
 */
public class MessagesErrorEvent extends ErrorEvent {

    public MessagesErrorEvent(@NonNull ProxerException exception) {
        super(exception);
    }
}
