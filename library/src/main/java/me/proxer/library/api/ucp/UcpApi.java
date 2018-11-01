package me.proxer.library.api.ucp;

import me.proxer.library.entity.ucp.UcpSettings;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
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
    public UcpApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public DeleteFavoriteEndpoint deleteFavorite(final String id) {
        return new DeleteFavoriteEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    public DeleteBookmarkEndpoint deleteBookmark(final String id) {
        return new DeleteBookmarkEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    public DeleteCommentEndpoint deleteComment(final String id) {
        return new DeleteCommentEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    public WatchedEpisodesEndpoint watchedEpisodes() {
        return new WatchedEpisodesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public UcpHistoryEndpoint history() {
        return new UcpHistoryEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public BookmarksEndpoint bookmarks() {
        return new BookmarksEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public UcpTopTenEndpoint topTen() {
        return new UcpTopTenEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public SetBookmarkEndpoint setBookmark(final String id, final int episode, final MediaLanguage language,
                                           final Category category) {
        return new SetBookmarkEndpoint(internalApi, id, episode, language, category);
    }

    /**
     * Returns the respective endpoint.
     */
    public UcpMediaListEndpoint mediaList() {
        return new UcpMediaListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public SettingsEndpoint settings() {
        return new SettingsEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public SetSettingsEndpoint setSettings(final UcpSettings settings) {
        return new SetSettingsEndpoint(internalApi, settings);
    }

    /**
     * Returns the respective endpoint.
     */
    public SetSettingsEndpoint setSettings() {
        return new SetSettingsEndpoint(internalApi);
    }
}
