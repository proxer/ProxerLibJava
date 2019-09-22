package me.proxer.library.api.wiki

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.wiki.WikiPage

/**
 * Endpoint for requesting the content of a proxer wiki page.
 *
 * @author Ruben Gees
 */
class WikiContentEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val title: String
) : Endpoint<WikiPage> {

    override fun build(): ProxerCall<WikiPage> {
        return internalApi.content(title)
    }
}
