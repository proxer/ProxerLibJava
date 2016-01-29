package com.proxerme.library.event;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.LoginUser;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class LoginEvent implements IEvent<LoginUser> {

    private LoginUser loginUser;

    public LoginEvent(@NonNull LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @NonNull
    @Override
    public LoginUser getItem() {
        return loginUser;
    }
}
