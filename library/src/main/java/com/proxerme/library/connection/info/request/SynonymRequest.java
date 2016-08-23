package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.InfoRequest;
import com.proxerme.library.connection.info.entity.Synonym;
import com.proxerme.library.connection.info.result.SynonymResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Class to create a object to request the Synonyms of a specified entry.
 *
 * @author Desnoo
 */
public class SynonymRequest extends InfoRequest<Synonym[]> {

    private static final String ENDPOINT = "names";

    private static final String ID_PARAMETER = "id";

    private String id;

    /**
     * Create a request object for the alternative names of an entry by its id.
     *
     * @param id the id of the entry (Anime/Manga) to request the name for.
     */
    public SynonymRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected ProxerResult<Synonym[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body) throws IOException {
        return moshi.adapter(SynonymResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String, ?>>asList(
                new Pair<>(ID_PARAMETER, id)
        );
    }
}
