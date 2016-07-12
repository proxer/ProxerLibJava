package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.user.result.UserInfoErrorResult;
import com.proxerme.library.connection.user.result.UserInfoResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class UserInfoRequest extends ProxerRequest<UserInfoResult, UserInfoErrorResult> {

    private static final String USERINFO_URL = "/api/v1/user/userinfo";

    private static final String USERID_FORM = "uid";
    private static final String USERNAME_FORM = "username";

    @Nullable
    private String userId;
    @Nullable
    private String username;

    public UserInfoRequest(@Nullable String userId, @Nullable String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    protected int getTag() {
        return ProxerTag.USERINFO;
    }

    @Override
    protected UserInfoResult parse(Response response) throws Exception {
        return response.asClass(UserInfoResult.class);
    }

    @Override
    protected UserInfoErrorResult createErrorResult(@NonNull ProxerException exception) {
        return new UserInfoErrorResult(exception);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + USERINFO_URL;
    }

    @Override
    protected void appendToBody(@NonNull Form form) {
        if (userId != null) {
            form.add(USERID_FORM, userId);
        }

        if (username != null) {
            form.add(USERNAME_FORM, username);
        }
    }
}
