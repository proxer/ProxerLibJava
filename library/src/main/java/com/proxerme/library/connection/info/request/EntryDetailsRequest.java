package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.InfoRequest;
import com.proxerme.library.connection.info.entity.EntryDetails;
import com.proxerme.library.connection.info.result.EntryDetailsResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request object for the details data of an entry by an id (Anime or Manga).
 *
 * @author Desnoo
 */
public class EntryDetailsRequest extends InfoRequest<EntryDetails> {

    private static final String ENDPOINT = "entry";

    private static final String ID_PARAMETER = "id";

    private String id;

    /**
     * Create a request object of an specific entry
     *
     * @param id the id of the entry to request.
     */
    public EntryDetailsRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected ProxerResult<EntryDetails> parse(@NonNull Moshi moshi, @NonNull ResponseBody body) throws IOException {
        return moshi.adapter(EntryDetailsResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String,?>>asList(
                new Pair<>(ID_PARAMETER, id)
        );
    }
}
