package me.proxer.library.api.messenger

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for creating a new conference with a single other user.
 *
 * Upon success, the result is a single string with the id of the newly created conference.
 *
 * @author Ruben Gees
 */
class CreateConferenceEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val firstMessage: String,
    private val username: String
) : Endpoint<String> {

    override fun build(): ProxerCall<String> {
        return internalApi.createConference(firstMessage, username)
    }
}
