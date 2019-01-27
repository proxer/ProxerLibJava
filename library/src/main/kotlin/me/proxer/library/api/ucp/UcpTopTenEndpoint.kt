package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.ucp.UcpTopTenEntry

/**
 * Endpoint for requesting the top ten list of the current user.
 *
 * @author Ruben Gees
 */
class UcpTopTenEndpoint internal constructor(
    private val internalApi: InternalApi
) : Endpoint<List<UcpTopTenEntry>> {

    override fun build(): ProxerCall<List<UcpTopTenEntry>> {
        return internalApi.topTen()
    }
}
