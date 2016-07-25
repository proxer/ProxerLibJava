package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.user.entitiy.User;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The result of a {@link com.proxerme.library.connection.user.request.LoginRequest}.
 *
 * @author Ruben Gees
 */
public class LoginResult implements ProxerResult<User> {

    @Body(name = "data")
    User user;

    /**
     * The constructor.
     *
     * @param user The now logged in user.
     */
    public LoginResult(@NonNull User user) {
        this.user = user;
    }

    LoginResult() {

    }

    /**
     * Returns the logged in User.
     *
     * @return The User.
     */
    @NonNull
    @Override
    public User getItem() {
        return user;
    }
}
