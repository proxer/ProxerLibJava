package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.user.entitiy.UserInfo;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class UserInfoResult implements ProxerResult<UserInfo> {

    @Body(name = "data")
    UserInfo item;

    @NonNull
    @Override
    public UserInfo getItem() {
        return item;
    }
}
