package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.entitiy.User;
import com.proxerme.library.connection.user.result.LoginResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class LoginRequest extends ProxerRequest<User> {

    private static final String CLASS = "user";
    private static final String ENDPOINT = "login";

    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";

    private String username;
    private String password;

    public LoginRequest(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected ProxerResult<User> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        LoginResult result = moshi.adapter(LoginResult.class).fromJson(body.source());

        if (result.getData() != null) {
            result.getData().setUsername(username);
            result.getData().setPassword(password);
        }

        return result;
    }

    @NonNull
    @Override
    protected Iterable<String> getEndpointPathSegments() {
        return Arrays.asList(CLASS, ENDPOINT);
    }

    @Override
    protected String getMethod() {
        return POST;
    }

    @Nullable
    @Override
    protected RequestBody getRequestBody() {
        return new FormBody.Builder()
                .add(USERNAME_PARAMETER, username)
                .add(PASSWORD_PARAMETER, password)
                .build();
    }
}
