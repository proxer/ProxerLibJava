package me.proxer.library.api.anime

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.anime.LinkContainer

/**
 * Endpoint similar to [LinkEndpoint], but additionally returns a VAST ad tag. This requires the highest authorization
 * level and should only be used by official applications.
 *
 * @author Ruben Gees
 */
class VastLinkEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<LinkContainer> {

    override fun build(): ProxerCall<LinkContainer> {
        return internalApi.vastLink(id)
    }
}
