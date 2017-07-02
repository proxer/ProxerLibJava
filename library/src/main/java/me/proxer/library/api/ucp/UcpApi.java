package me.proxer.library.api.ucp;

import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * API for the Ucp class.
 *
 * @author Ruben Gees
 */
public final class UcpApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public UcpApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public DeleteFavoriteEndpoint deleteFavorite(@NotNull final String id) {
        return new DeleteFavoriteEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public DeleteBookmarkEndpoint deleteBookmark(@NotNull final String id) {
        return new DeleteBookmarkEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public WatchedEpisodesEndpoint watchedEpisodes() {
        return new WatchedEpisodesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public UcpHistoryEndpoint history() {
        return new UcpHistoryEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public BookmarksEndpoint bookmarks() {
        return new BookmarksEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public UcpTopTenEndpoint topTen() {
        return new UcpTopTenEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public SetBookmarkEndpoint setBookmark(@NotNull final String id, final int episode,
                                           @NotNull final MediaLanguage language, @NotNull final Category category) {
        return new SetBookmarkEndpoint(internalApi, id, episode, language, category);
    }
}
