package com.proxerme.library.connection.notifications.result;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.interfaces.ProxerErrorResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class NewsErrorResult extends ProxerErrorResult {
    public NewsErrorResult(@NonNull ProxerException exception) {
        super(exception);
    }
}
