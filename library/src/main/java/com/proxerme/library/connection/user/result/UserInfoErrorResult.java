package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.interfaces.ProxerErrorResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class UserInfoErrorResult extends ProxerErrorResult {
    public UserInfoErrorResult(@NonNull ProxerException exception) {
        super(exception);
    }
}
