package me.proxer.library.api.manga;

import me.proxer.library.enums.Language;
import org.jetbrains.annotations.NotNull;
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
    public MangaApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ChapterEndpoint chapter(@NotNull final String entryId, int episode, @NotNull Language language) {
        return new ChapterEndpoint(internalApi, entryId, episode, language);
    }
}
