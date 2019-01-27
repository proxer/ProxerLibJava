package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.info.Recommendation

/**
 * Endpoint for retrieving the most important information of an [Recommendation].
 *
 * @author Ruben Gees
 */
class RecommendationsEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<List<Recommendation>> {

    override fun build(): ProxerCall<List<Recommendation>> {
        return internalApi.recommendations(id)
    }
}
