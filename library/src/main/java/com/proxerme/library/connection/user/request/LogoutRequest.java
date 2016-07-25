package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.user.result.LogoutResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request for logging the user out. No checks need to be done here, this even works if no user was
 * logged in before.
 *
 * @author Ruben Gees
 */

public class LogoutRequest extends ProxerRequest<LogoutResult> {

    private static final String LOGOUT_URL = "/api/v1/user/logout";

    @Override
    protected LogoutResult parse(Response response) throws Exception {
        return new LogoutResult();
    }

    @ProxerTag.ConnectionTag
    @Override
    protected int getTag() {
        return ProxerTag.LOGOUT;
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + LOGOUT_URL;
    }
}
