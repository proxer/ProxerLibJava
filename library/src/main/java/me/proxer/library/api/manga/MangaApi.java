package me.proxer.library.api.manga;

import me.proxer.library.enums.Language;
import retrofit2.Retrofit;

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
    public MangaApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public ChapterEndpoint chapter(final String entryId, final int episode, final Language language) {
        return new ChapterEndpoint(internalApi, entryId, episode, language);
    }
}
