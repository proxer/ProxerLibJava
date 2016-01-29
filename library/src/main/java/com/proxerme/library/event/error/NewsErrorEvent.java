package com.proxerme.library.event.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class NewsErrorEvent extends ErrorEvent {
    public NewsErrorEvent(@NonNull ProxerException exception) {
        super(exception);
    }
}
