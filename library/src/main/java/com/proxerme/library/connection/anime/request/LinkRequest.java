package com.proxerme.library.connection.anime.request;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.anime.AnimeRequest;
import com.proxerme.library.connection.anime.result.LinkResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Collections;

import okhttp3.ResponseBody;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class LinkRequest extends AnimeRequest<String> {

    private static final String ENDPOINT = "link";

    private static final String ID_PARAMETER = "id";

    private String id;

    /**
     * The constructor.
     *
     * @param id The id of the stream.
     */
    public LinkRequest(@NonNull String id) {
        this.id = id;
    }

    @Override
    protected ProxerResult<String> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(LinkResult.class).fromJson(body.source());
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
