package com.proxerme.library.connection.ucp.request;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.UcpRequest;
import com.proxerme.library.connection.ucp.result.ListsumResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Request for the amount of watched episodes. The user needs to be logged in for this APO.
 *
 * @author Ruben Gees
 */
public class ListsumRequest extends UcpRequest<Integer> {

    private static final String ENDPOINT = "listsum";

    @Override
    protected ProxerResult<Integer> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ListsumResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }
}
