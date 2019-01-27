package me.proxer.library.api.chat

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall

/**
 * Endpoint for reporting a message to the moderators.
 *
 * @author Ruben Gees
 */
class ReportChatMessageEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val messageId: String,
    private val message: String
) : Endpoint<Unit> {

    override fun build(): ProxerCall<Unit> {
        return internalApi.reportMessage(messageId, message)
    }
}
