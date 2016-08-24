package com.proxerme.library.connection.user.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.entitiy.UserInfo;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class UserInfoResult extends ProxerResult<UserInfo> {

    @Json(name = "data")
    private UserInfo userInfo;

    protected UserInfoResult() {

    }

    @Override
    public UserInfo getData() {
        return userInfo;
    }
}
