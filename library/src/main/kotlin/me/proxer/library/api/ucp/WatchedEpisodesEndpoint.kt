package me.proxer.library.api.ucp

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall

/**
 * Endpoint for requesting the amount of watched episodes of the current user.
 *
 * @author Ruben Gees
 */
class WatchedEpisodesEndpoint internal constructor(
    private val internalApi: InternalApi
) : Endpoint<Int> {

    override fun build(): ProxerCall<Int> {
        return internalApi.watchedEpisodes()
    }
}
