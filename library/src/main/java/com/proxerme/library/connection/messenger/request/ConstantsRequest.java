package com.proxerme.library.connection.messenger.request;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.MessengerRequest;
import com.proxerme.library.connection.messenger.entity.Constants;
import com.proxerme.library.connection.messenger.result.ConstantsResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Request for the constants of the messenger API.
 *
 * @author Desnoo
 */
public class ConstantsRequest extends MessengerRequest<Constants> {

    private static final String ENDPOINT = "constants";

    @Override
    protected ProxerResult<Constants> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ConstantsResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }
}
