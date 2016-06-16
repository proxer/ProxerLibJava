package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.RequestBuilder;
import com.afollestad.bridge.Response;
import com.afollestad.bridge.ResponseValidator;
import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.user.result.LogoutErrorResult;
import com.proxerme.library.connection.user.result.LogoutResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class LogoutRequest extends ProxerRequest<LogoutResult, LogoutErrorResult> {

    private static final String LOGOUT_URL = "/login?format=json&action=logout";

    @NonNull
    @Override
    protected RequestBuilder beginRequest() {
        return Bridge.post(ProxerUrlHolder.getHost() + LOGOUT_URL);
    }

    @Override
    protected LogoutResult parse(Response response) throws BridgeException {
        return new LogoutResult();
    }

    @ProxerTag.ConnectionTag
    @Override
    protected int getTag() {
        return ProxerTag.LOGOUT;
    }

    @Override
    protected LogoutErrorResult createErrorResult(@NonNull ProxerException exception) {
        return new LogoutErrorResult(exception);
    }

    @NonNull
    @Override
    protected ResponseValidator getValidator() {
        return new LoginLogoutResponseValidator();
    }
}
