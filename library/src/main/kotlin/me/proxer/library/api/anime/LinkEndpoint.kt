package me.proxer.library.api.anime

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall

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
) : Endpoint<String> {

    override fun build(): ProxerCall<String> {
        return internalApi.link(id)
    }
}
