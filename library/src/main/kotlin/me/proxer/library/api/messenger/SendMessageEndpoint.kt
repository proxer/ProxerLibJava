package me.proxer.library.api.messenger

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.messenger.Conference

/**
 * Endpoint for sending a single message in a [Conference]. This can be a group or a conversation with a single user.
 *
 * Upon success, the result is null. In some cases, an error is not returned normally, but as the message returned from
 * the request.
 *
 * @author Ruben Gees
 */
class SendMessageEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val conferenceId: String,
    private val message: String
) : Endpoint<String> {

    override fun build(): ProxerCall<String> {
        return internalApi.sendMessage(conferenceId, message)
    }
}
