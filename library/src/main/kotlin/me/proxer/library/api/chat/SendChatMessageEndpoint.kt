package me.proxer.library.api.chat

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for sending a single message in a [me.proxer.library.entity.chat.ChatRoom].
 *
 * Upon success, the id of the message is returned.
 *
 * @author Ruben Gees
 */
class SendChatMessageEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val roomId: String,
    private val message: String
) : Endpoint<String> {

    override fun build(): ProxerCall<String> {
        return internalApi.sendMessage(roomId, message)
    }
}
