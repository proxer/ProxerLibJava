package com.proxerme.library.connection.experimental.messages.result;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.interfaces.ProxerErrorResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class ConferencesErrorResult extends ProxerErrorResult {

    public ConferencesErrorResult(@NonNull ProxerException exception) {
        super(exception);
    }
}
