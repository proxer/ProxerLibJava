package me.proxer.library.api.users

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for requesting the descriptions of the current teams.
 *
 * @author Ruben Gees
 */
class TeamDescriptionEndpoint internal constructor(
    private val internalApi: InternalApi
) : Endpoint<Map<String, String>> {

    override fun build(): ProxerCall<Map<String, String>> {
        return internalApi.teamDescriptions()
    }
}
