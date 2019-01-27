package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

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
