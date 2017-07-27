package me.proxer.library.api.manga;

import me.proxer.library.enums.Language;
import retrofit2.Retrofit;

import javax.annotation.Nonnull;

/**
 * API for the Manga class.
 *
 * @author Ruben Gees
 */
public final class MangaApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public MangaApi(@Nonnull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ChapterEndpoint chapter(@Nonnull final String entryId, int episode, @Nonnull Language language) {
        return new ChapterEndpoint(internalApi, entryId, episode, language);
    }
}
