package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Response;
import com.afollestad.bridge.ResponseValidator;
import com.proxerme.library.connection.ProxerException;

import org.json.JSONObject;

import static com.proxerme.library.connection.ProxerException.ERROR_PROXER;
import static com.proxerme.library.connection.ProxerException.ERROR_UNKNOWN;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

class LoginLogoutResponseValidator extends ResponseValidator {

    private static final String RESPONSE_ERROR = "error";
    private static final String RESPONSE_ERROR_MESSAGE = "message";
    private static final String VALIDATOR_ID = "login-logout-validator";

    @Override
    public boolean validate(@NonNull Response response) throws Exception {
        JSONObject json = response.asJsonObject();

        if (json != null && json.has(RESPONSE_ERROR)) {
            if (json.getInt(RESPONSE_ERROR) == 0) {
                return true;
            } else {
                if (json.has(RESPONSE_ERROR_MESSAGE)) {
                    throw new ProxerException(ERROR_PROXER,
                            json.getString(RESPONSE_ERROR_MESSAGE));
                } else {
                    throw new ProxerException(ERROR_UNKNOWN);
                }
            }
        } else {
            return false;
        }
    }

    @NonNull
    @Override
    public String id() {
        return VALIDATOR_ID;
    }
}
