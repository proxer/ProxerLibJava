package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.InfoRequest;
import com.proxerme.library.connection.info.entity.Industry;
import com.proxerme.library.connection.info.result.IndustryResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;

import okhttp3.ResponseBody;

/**
 * Request for getting more info on a industry, based on its id.
 *
 * @author Ruben Gees
 */
public class IndustryRequest extends InfoRequest<Industry> {

    private static final String ENDPOINT = "industry";

    private static final String ID_PARAMETER = "id";

    private String id;

    /**
     * The constructor.
     *
     * @param id The id of the industry.
     */
    public IndustryRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected ProxerResult<Industry> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(IndustryResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Collections.<Pair<String, ?>>singletonList(
                new Pair<>(ID_PARAMETER, id)
        );
    }
}
