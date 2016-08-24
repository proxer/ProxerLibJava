package com.proxerme.library.connection.user.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.entitiy.User;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class LoginResult extends ProxerResult<User> {

    @Json(name = "data")
    private User user;

    protected LoginResult() {
    }

    @Override
    public User getData() {
        return user;
    }
}
