package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.ucp.UcpHistoryEntry

/**
 * Endpoint for requesting the history of the current user.
 *
 * @author Ruben Gees
 */
class UcpHistoryEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<UcpHistoryEntry>> {

    private var page: Int? = null
    private var limit: Int? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    override fun build(): ProxerCall<List<UcpHistoryEntry>> {
        return internalApi.history(page, limit)
    }
}
