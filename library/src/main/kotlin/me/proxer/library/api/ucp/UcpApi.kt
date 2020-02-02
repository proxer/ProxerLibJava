package me.proxer.library.api.ucp

import me.proxer.library.entity.ucp.UcpSettings
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import retrofit2.Retrofit

/**
 * API for the Ucp class.
 *
 * @author Ruben Gees
 */
class UcpApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun deleteFavorite(id: String): DeleteFavoriteEndpoint {
        return DeleteFavoriteEndpoint(internalApi, id)
    }

    /**
     * Returns the respective endpoint.
     */
    fun deleteBookmark(id: String): DeleteBookmarkEndpoint {
        return DeleteBookmarkEndpoint(internalApi, id)
    }

    /**
     * Returns the respective endpoint.
     */
    fun deleteComment(id: String): DeleteCommentEndpoint {
        return DeleteCommentEndpoint(internalApi, id)
    }

    /**
     * Returns the respective endpoint.
     */
    fun deleteSubscription(id: String): DeleteSubscriptionEndpoint {
        return DeleteSubscriptionEndpoint(internalApi, id)
    }

    /**
     * Returns the respective endpoint.
     */
    fun watchedEpisodes(): WatchedEpisodesEndpoint {
        return WatchedEpisodesEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun history(): UcpHistoryEndpoint {
        return UcpHistoryEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun bookmarks(): BookmarksEndpoint {
        return BookmarksEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun topTen(): UcpTopTenEndpoint {
        return UcpTopTenEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun setBookmark(id: String, episode: Int, language: MediaLanguage, category: Category): SetBookmarkEndpoint {
        return SetBookmarkEndpoint(internalApi, id, episode, language, category)
    }

    /**
     * Returns the respective endpoint.
     */
    fun mediaList(): UcpMediaListEndpoint {
        return UcpMediaListEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun settings(): SettingsEndpoint {
        return SettingsEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun setSettings(settings: UcpSettings): SetSettingsEndpoint {
        return SetSettingsEndpoint(internalApi, settings)
    }

    /**
     * Returns the respective endpoint.
     */
    fun setSettings(): SetSettingsEndpoint {
        return SetSettingsEndpoint(internalApi)
    }
}
