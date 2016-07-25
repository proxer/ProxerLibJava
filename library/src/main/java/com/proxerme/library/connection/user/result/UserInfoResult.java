package com.proxerme.library.connection.user.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.user.entitiy.UserInfo;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The Result of a {@link com.proxerme.library.connection.user.request.UserInfoRequest}.
 *
 * @author Ruben Gees
 */

public class UserInfoResult implements ProxerResult<UserInfo> {

    @Body(name = "data")
    UserInfo item;

    UserInfoResult() {

    }

    /**
     * The constructor.
     *
     * @param item The UserInfo.
     */
    public UserInfoResult(@NonNull UserInfo item) {
        this.item = item;
    }

    /**
     * Returns the info of a user.
     *
     * @return The UserInfo.
     */
    @NonNull
    @Override
    public UserInfo getItem() {
        return item;
    }
}
