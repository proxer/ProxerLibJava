package me.proxer.library.api.info

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.info.Industry

/**
 * Endpoint for retrieving all information of an [Industry].
 *
 * @author Ruben Gees
 */
class IndustryEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Industry> {

    override fun build(): ProxerCall<Industry> {
        return internalApi.industry(id)
    }
}
