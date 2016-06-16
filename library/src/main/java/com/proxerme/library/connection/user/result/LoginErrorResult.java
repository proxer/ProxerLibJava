package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.interfaces.ProxerErrorResult;

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
