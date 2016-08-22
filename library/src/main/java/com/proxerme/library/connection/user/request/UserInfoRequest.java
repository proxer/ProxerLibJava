package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.entitiy.UserInfo;
import com.proxerme.library.connection.user.result.UserInfoResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Request for the basic info of a User. Those are always public. Included are the rank points, the
 * status and the avatar image.
 *
 * @author Ruben Gees
 */
public class UserInfoRequest extends ProxerRequest<UserInfo> {

    @Nullable
    private String userId;
    @Nullable
    private String username;

    /**
     * The constructor. You can either pass the id of the user, the name of the user or none of
     * both.
     * If you pass none, the logged in user will be returned. If you pass both id and name, the name
     * will be ignored.
     *
     * @param userId   The id of the user.
     * @param username The name of the user.
     */
    public UserInfoRequest(@Nullable String userId, @Nullable String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    public ProxerResult<UserInfo> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(UserInfoResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected Iterable<String> getEndpointPathSegments() {
        return Arrays.asList("user", "userinfo");
    }

    @NonNull
    @Override
    protected Map<String, String> getQueryParameters() {
        HashMap<String, String> result = new HashMap<>(2);

        if (userId != null) {
            result.put("uid", userId);
        }

        if (username != null) {
            result.put("username", username);
        }

        return result;
    }
}