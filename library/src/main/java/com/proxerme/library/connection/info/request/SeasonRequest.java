package com.proxerme.library.connection.info.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.InfoRequest;
import com.proxerme.library.connection.info.entity.Season;
import com.proxerme.library.connection.info.result.SeasonResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;

import okhttp3.ResponseBody;

/**
 * Class that represents the request of a season request.
 *
 * @author Desnoo
 */
public class SeasonRequest extends InfoRequest<Season[]> {

    private static final String ENDPOINT = "season";
    private static final String ID_PARAMETER = "id";

    private String id;

    /**
     * The Constructor of a request of a season for an entry (Anime, Manga)
     *
     * @param id The entry id to request.
     */
    public SeasonRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected ProxerResult<Season[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(SeasonResult.class).fromJson(body.source());
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
