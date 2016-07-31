package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.user.result.UserInfoResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request for the basic info of a User. Those are always public. Included are the rank points, the
 * status and the avatar image.
 *
 * @author Ruben Gees
 */

public class UserInfoRequest extends ProxerRequest<UserInfoResult> {

    private static final String USERINFO_URL = "/api/v1/user/userinfo";

    private static final String USERID_FORM = "uid";
    private static final String USERNAME_FORM = "username";

    @Nullable
    private String userId;
    @Nullable
    private String username;

    /**
     * The constructor. You can choose if you want to pass the id or the name of the user, but must
     * pass at least one. If you pass both, the username is discarded by the API.
     *
     * @param userId   The id of the user.
     * @param username The name of the user.
     */
    public UserInfoRequest(@Nullable String userId, @Nullable String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    protected int getTag() {
        return ProxerTag.USERINFO;
    }

    @Override
    protected UserInfoResult parse(@NonNull Response response) throws Exception {
        return response.asClass(UserInfoResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + USERINFO_URL;
    }

    @Override
    protected Form getBody() {
        Form form = new Form();

        if (userId != null) {
            form.add(USERID_FORM, userId);
        }

        if (username != null) {
            form.add(USERNAME_FORM, username);
        }

        return form;
    }
}
