package com.proxerme.library.api.ucp;

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
    public DeleteFavoriteRequest deleteFavorite(@NotNull final String id) {
        return new DeleteFavoriteRequest(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public DeleteBookmarkRequest deleteBookmark(@NotNull final String id) {
        return new DeleteBookmarkRequest(internalApi, id);
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
}
