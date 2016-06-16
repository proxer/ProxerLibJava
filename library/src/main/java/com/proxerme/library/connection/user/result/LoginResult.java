package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.user.entitiy.LoginUser;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class LoginResult implements ProxerResult<LoginUser> {

    LoginUser loginUser;

    public LoginResult(@NonNull LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @NonNull
    @Override
    public LoginUser getItem() {
        return loginUser;
    }
}
