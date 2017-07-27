package me.proxer.library.api.anime;

import me.proxer.library.enums.AnimeLanguage;
import retrofit2.Retrofit;

import javax.annotation.Nonnull;

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
    public AnimeApi(@Nonnull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public StreamsEndpoint streams(@Nonnull final String entryId, final int episode,
                                   @Nonnull final AnimeLanguage language) {
        return new StreamsEndpoint(internalApi, entryId, episode, language);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public LinkEndpoint link(@Nonnull final String streamId) {
        return new LinkEndpoint(internalApi, streamId);
    }
}
