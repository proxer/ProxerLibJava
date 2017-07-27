package me.proxer.library.api.ucp;

import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import retrofit2.Retrofit;

import javax.annotation.Nonnull;

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
    public UcpApi(@Nonnull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public DeleteFavoriteEndpoint deleteFavorite(@Nonnull final String id) {
        return new DeleteFavoriteEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public DeleteBookmarkEndpoint deleteBookmark(@Nonnull final String id) {
        return new DeleteBookmarkEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public WatchedEpisodesEndpoint watchedEpisodes() {
        return new WatchedEpisodesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public UcpHistoryEndpoint history() {
        return new UcpHistoryEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public BookmarksEndpoint bookmarks() {
        return new BookmarksEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public UcpTopTenEndpoint topTen() {
        return new UcpTopTenEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public SetBookmarkEndpoint setBookmark(@Nonnull final String id, final int episode,
                                           @Nonnull final MediaLanguage language, @Nonnull final Category category) {
        return new SetBookmarkEndpoint(internalApi, id, episode, language, category);
    }
}
