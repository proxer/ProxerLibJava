package me.proxer.library.api.ucp

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall

/**
 * Endpoint for unmarking an entry as favorite.
 *
 * @author Ruben Gees
 */
class DeleteFavoriteEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Unit> {

    override fun build(): ProxerCall<Unit> {
        return internalApi.deleteFavorite(id)
    }
}
