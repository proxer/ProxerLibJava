package me.proxer.library.api.anime

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.anime.LinkContainer
import me.proxer.library.internal.util.toIntOrNull

/**
 * Endpoint for retrieving the link to the uploaded anime. This may be null, if the link is broken or has been deleted.
 *
 * Note, that these links point to the web page and not the final data source.
 *
 * @author Ruben Gees
 */
class LinkEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<LinkContainer> {

    private var enableAds: Boolean? = null

    /**
     * Sets if the ad system of Proxer is supported and should be enabled.
     * Requires a special permission from the administration.
     */
    fun enableAds(enableAds: Boolean? = false) = this.apply {
        this.enableAds = enableAds
    }

    override fun build(): ProxerCall<LinkContainer> {
        return internalApi.link(id, enableAds.toIntOrNull())
    }
}
