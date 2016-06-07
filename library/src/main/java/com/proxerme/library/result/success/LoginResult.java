package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.LoginUser;
import com.proxerme.library.result.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class LoginResult implements ProxerResult<LoginUser> {

    private LoginUser loginUser;

    public LoginResult(@NonNull LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @NonNull
    @Override
    public LoginUser getItem() {
        return loginUser;
    }
}
