package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.InfoRequest;
import com.proxerme.library.connection.info.entity.Entry;
import com.proxerme.library.connection.info.result.EntryResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;

import okhttp3.ResponseBody;

/**
 * Request for ALL data of an entry. This API should only be used if really needed as it is
 * resource hungry.
 *
 * @author Ruben Gees
 */
public class EntryRequest extends InfoRequest<Entry> {

    private static final String ENDPOINT = "fullentry";
    private static final String ID_PARAMETER = "id";

    private String id;

    /**
     * The constructor.
     *
     * @param id The id of the entry.
     */
    public EntryRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected ProxerResult<Entry> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(EntryResult.class).fromJson(body.source());
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
