package me.proxer.library.api.chat

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.chat.ChatMessage

/**
 * Endpoint for retrieving messages in a chat room.
 *
 * This behaves differently based on the parameter messageId:
 * 1) messageId = 0: Returns the last messages from the chat room.
 * 2) messageId != 0: Returns all messages older than that passed from the chat room.
 *
 * @author Ruben Gees
 */
class ChatMessagesEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val roomId: String
) : Endpoint<List<ChatMessage>> {

    private var messageId: String? = null

    /**
     * Sets the message id to load from.
     */
    fun messageId(messageId: String?) = this.apply { this.messageId = messageId }

    override fun build(): ProxerCall<List<ChatMessage>> {
        return internalApi.messages(roomId, messageId)
    }
}
