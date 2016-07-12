package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.user.entitiy.User;
import com.proxerme.library.connection.user.result.LoginErrorResult;
import com.proxerme.library.connection.user.result.LoginResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class LoginRequest extends ProxerRequest<LoginResult, LoginErrorResult> {

    private static final String LOGIN_URL = "/api/v1/user/login";

    private static final String USERNAME_FORM = "username";
    private static final String PASSWORD_FORM = "password";

    private User user;

    public LoginRequest(@NonNull User user) {
        this.user = user;
    }

    @Override
    protected LoginResult parse(Response response) throws Exception {
        LoginResult result = response.asClass(LoginResult.class);

        result.getItem().setUsername(user.getUsername());
        result.getItem().setPassword(user.getPassword());

        return result;
    }

    @Override
    protected LoginErrorResult createErrorResult(@NonNull ProxerException exception) {
        return new LoginErrorResult(exception);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + LOGIN_URL;
    }

    @Override
    protected void appendToBody(@NonNull Form form) {
        form.add(USERNAME_FORM, user.getUsername())
                .add(PASSWORD_FORM, user.getPassword());
    }

    @ProxerTag.ConnectionTag
    @Override
    protected int getTag() {
        return ProxerTag.LOGIN;
    }
}
