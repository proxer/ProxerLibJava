package com.proxerme.library.api.anime;

import com.proxerme.library.enums.AnimeLanguage;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * API for the Anime class.
 *
 * @author Ruben Gees
 */
public final class AnimeApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public AnimeApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public StreamsEndpoint streams(@NotNull final String entryId, final int episode,
                                   @NotNull final AnimeLanguage language) {
        return new StreamsEndpoint(internalApi, entryId, episode, language);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public LinkEndpoint link(@NotNull final String streamId) {
        return new LinkEndpoint(internalApi, streamId);
    }
}
