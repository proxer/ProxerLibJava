package com.proxerme.library.connection.manga.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.manga.MangaRequest;
import com.proxerme.library.connection.manga.entity.Chapter;
import com.proxerme.library.connection.manga.result.ChapterResult;
import com.proxerme.library.parameters.GeneralLanguageParameter.GeneralLanguage;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for retrieving information on a single chapter of the specified manga. A user must be
 * logged in to earn points.
 *
 * @author Ruben Gees
 */
public class ChapterRequest extends MangaRequest<Chapter> {

    private static final String ENDPOINT = "chapter";

    private static final String ID_PARAMETER = "id";
    private static final String EPISODE_PARAMETER = "episode";
    private static final String LANGUAGE_PARAMETER = "language";

    private String id;
    private int episode;
    private String language;

    /**
     * The constructor.
     *
     * @param id       The id of the manga.
     * @param episode  The episode.
     * @param language The selected language.
     */
    public ChapterRequest(@NonNull String id, @IntRange(from = 1) int episode,
                          @NonNull @GeneralLanguage String language) {
        this.id = id;
        this.episode = episode;
        this.language = language;
    }

    @Override
    protected ProxerResult<Chapter> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ChapterResult.class).fromJson(body.source());
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
                new Pair<>(ID_PARAMETER, id),
                new Pair<>(EPISODE_PARAMETER, episode),
                new Pair<>(LANGUAGE_PARAMETER, language)
        );
    }
}
