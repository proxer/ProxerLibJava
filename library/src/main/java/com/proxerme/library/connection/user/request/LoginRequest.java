package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.user.entitiy.User;
import com.proxerme.library.connection.user.result.LoginResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request for logging the user in. If the passed user or another user is logged in already, the API
 * will return an error. In this case you have to log out with the {@link LogoutRequest}. Moreover
 * you need to save Cookies for other Requests depending on a logged in user to work. You can use
 * the {@link com.proxerme.library.util.PersistentCookieStore}.
 *
 * @author Ruben Gees
 */
public class LoginRequest extends ProxerRequest<LoginResult> {

    private static final String LOGIN_URL = "/api/v1/user/login";

    private static final String USERNAME_FORM = "username";
    private static final String PASSWORD_FORM = "password";

    private User user;

    /**
     * The constructor.
     *
     * @param user The user to log in.
     */
    public LoginRequest(@NonNull User user) {
        this.user = user;
    }

    @Override
    protected LoginResult parse(Response response) throws Exception {
        LoginResult result = response.asClass(LoginResult.class);

        //noinspection ConstantConditions
        result.getItem().setUsername(user.getUsername());
        result.getItem().setPassword(user.getPassword());

        return result;
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
