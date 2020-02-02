package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for deleting a subscription on an entry.
 *
 * @author Ruben Gees
 */
class DeleteSubscriptionEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Unit> {

    override fun build(): ProxerCall<Unit> {
        return internalApi.deleteSubscription(id)
    }
}
