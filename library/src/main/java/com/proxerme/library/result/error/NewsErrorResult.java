package com.proxerme.library.result.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

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
