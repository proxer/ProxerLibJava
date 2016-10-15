package com.proxerme.library.connection.user.request;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.UserRequest;
import com.proxerme.library.connection.user.result.LogoutResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Request for logging the user out. No checks need to be done here, this even works if no user was
 * logged in before. Upon success, null is returned int the
 * {@link com.proxerme.library.connection.ProxerCallback}. Otherwise the
 * {@link com.proxerme.library.connection.ProxerErrorCallback} is invoked.
 *
 * @author Ruben Gees
 */
public class LogoutRequest extends UserRequest<Void> {

    private static final String ENDPOINT = "logout";

    @Override
    protected ProxerResult<Void> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(LogoutResult.class).lenient().fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }
}
