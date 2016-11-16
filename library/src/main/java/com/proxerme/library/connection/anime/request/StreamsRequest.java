package com.proxerme.library.connection.anime.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.anime.AnimeRequest;
import com.proxerme.library.connection.anime.entity.Stream;
import com.proxerme.library.connection.anime.result.StreamResult;
import com.proxerme.library.parameters.GeneralLanguageParameter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for retrieving all streams for a specified episode of an anime (by id) with a specific
 * language.
 *
 * @author Ruben Gees
 */
public class StreamsRequest extends AnimeRequest<Stream[]> {

    private static final String STREAMS = "streams";
    private static final String STREAMS_WITH_PROXER = "proxerstreams";

    private static final String ID_PARAMETER = "id";
    private static final String EPISODE_PARAMETER = "episode";
    private static final String LANGUAGE_PARAMETER = "language";

    private String id;
    private int episode;
    private String language;

    private boolean includeProxerStreams;

    /**
     * The constructor.
     *
     * @param id       The id of the anime.
     * @param episode  The episode.
     * @param language The selected language.
     */
    public StreamsRequest(@NonNull String id, @IntRange(from = 1) int episode,
                          @NonNull @GeneralLanguageParameter.GeneralLanguage String language) {
        this.id = id;
        this.episode = episode;
        this.language = language;
    }

    /**
     * Sets if Proxer-streams should be included. Those require the highest permission level 3.
     *
     * @param includeProxerStreams True if streams should be included. The request will fail and not
     *                             return if the user has not sufficient rights.
     * @return This request.
     */
    public StreamsRequest withIncludeProxerStreams(boolean includeProxerStreams) {
        this.includeProxerStreams = includeProxerStreams;

        return this;
    }

    @Override
    protected ProxerResult<Stream[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(StreamResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        if (includeProxerStreams) {
            return STREAMS_WITH_PROXER;
        } else {
            return STREAMS;
        }
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String, ?>>asList(
                new Pair<>(ID_PARAMETER, id),
                new Pair<>(EPISODE_PARAMETER, episode),
                new Pair<>(LANGUAGE_PARAMETER, language)
        );
    }
}
