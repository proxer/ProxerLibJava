package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.user.entitiy.User;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class LoginResult implements ProxerResult<User> {

    @Body(name = "data")
    User user;

    public LoginResult(@NonNull User user) {
        this.user = user;
    }

    LoginResult() {

    }

    @NonNull
    @Override
    public User getItem() {
        return user;
    }
}
