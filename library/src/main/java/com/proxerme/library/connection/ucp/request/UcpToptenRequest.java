package com.proxerme.library.connection.ucp.request;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.UcpRequest;
import com.proxerme.library.connection.ucp.entitiy.UcpToptenEntity;
import com.proxerme.library.connection.ucp.result.UcpToptenResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Request for retrieving the topten entries of the logged in user. This are both anime and manga
 * entries.
 *
 * @author Ruben Gees
 */
public class UcpToptenRequest extends UcpRequest<UcpToptenEntity[]> {

    private static final String ENDPOINT = "topten";

    @Override
    protected ProxerResult<UcpToptenEntity[]> parse(@NonNull Moshi moshi,
                                                    @NonNull ResponseBody body) throws IOException {
        return moshi.adapter(UcpToptenResult.class).lenient().fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

}
