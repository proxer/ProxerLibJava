package me.proxer.library.api.messenger

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for creating a new conference group with one or more other participants.
 *
 * Upon success, the result is a single string with the id of the newly created conference.
 *
 * @author Ruben Gees
 */
class CreateConferenceGroupEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val topic: String,
    private val firstMessage: String,
    private val participants: List<String>
) : Endpoint<String> {

    override fun build(): ProxerCall<String> {
        return internalApi.createConferenceGroup(topic, firstMessage, participants)
    }
}
