package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.info.Entry
import me.proxer.library.entity.info.EpisodeInfo

/**
 * Endpoint for retrieving a list of episodes and associated meta information of an [Entry].
 *
 * @author Ruben Gees
 */
class EpisodeInfoEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : PagingLimitEndpoint<EpisodeInfo> {

    private var page: Int? = null
    private var limit: Int? = null

    private var includeNotAvailableEpisodes: Boolean? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets if episodes should be included which are linked, but not uploaded yet.
     */
    fun includeNotAvailableEpisodes(includeNotAvailableEpisodes: Boolean?) = this.apply {
        this.includeNotAvailableEpisodes = includeNotAvailableEpisodes
    }

    override fun build(): ProxerCall<EpisodeInfo> {
        return internalApi.episodeInfo(id, page, limit, includeNotAvailableEpisodes)
    }
}
