package com.proxerme.library.result.error;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class LoginErrorResult extends ProxerErrorResult {
    public LoginErrorResult(@NonNull ProxerException exception) {
        super(exception);
    }
}
