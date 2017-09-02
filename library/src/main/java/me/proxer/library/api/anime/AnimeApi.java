package me.proxer.library.api.anime;

import me.proxer.library.enums.AnimeLanguage;
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
    public AnimeApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public StreamsEndpoint streams(final String entryId, final int episode, final AnimeLanguage language) {
        return new StreamsEndpoint(internalApi, entryId, episode, language);
    }

    /**
     * Returns the respective endpoint.
     */
    public LinkEndpoint link(final String streamId) {
        return new LinkEndpoint(internalApi, streamId);
    }
}
