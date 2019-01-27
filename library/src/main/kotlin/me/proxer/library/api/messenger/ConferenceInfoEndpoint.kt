package me.proxer.library.api.messenger

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.messenger.Conference
import me.proxer.library.entity.messenger.ConferenceInfo

/**
 * Endpoint for retrieving information concerning a [Conference].
 *
 * @author Ruben Gees
 */
class ConferenceInfoEndpoint internal constructor
    (
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<ConferenceInfo> {

    override fun build(): ProxerCall<ConferenceInfo> {
        return internalApi.conferenceInfo(id)
    }
}
