package com.proxerme.library.connection;

import android.support.annotation.NonNull;

import com.afollestad.bridge.Response;
import com.afollestad.bridge.ResponseValidator;

import org.json.JSONObject;

import static com.proxerme.library.connection.ProxerException.PROXER;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

class DefaultValidator extends ResponseValidator {

    private static final String RESPONSE_ERROR = "error";
    private static final String RESPONSE_ERROR_MESSAGE = "message";
    private static final String RESPONSE_ERROR_CODE = "code";
    private static final String VALIDATOR_ID = "default-validator";

    @Override
    public boolean validate(@NonNull Response response) throws Exception {
        JSONObject json = response.asJsonObject();

        if (json != null && json.has(RESPONSE_ERROR)) {
            if (json.getInt(RESPONSE_ERROR) == 0) {
                return true;
            } else {
                if (json.has(RESPONSE_ERROR_MESSAGE) && json.has(RESPONSE_ERROR_CODE)) {
                    throw new ProxerException(PROXER,
                            json.getString(RESPONSE_ERROR_MESSAGE));
                } else {
                    throw new ProxerException(ProxerException.UNKNOWN);
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
