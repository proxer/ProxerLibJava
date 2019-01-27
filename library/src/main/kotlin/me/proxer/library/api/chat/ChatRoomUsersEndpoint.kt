package me.proxer.library.api.chat

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.chat.ChatRoomUser

/**
 * Endpoint for retrieving messages in a chat room.
 *
 *
 * This behaves differently based on the parameter messageId:
 * 1) messageId = 0: Returns the last messages from the chat room.
 * 2) messageId != 0: Returns all messages older than that passed from the chat room.
 *
 * @author Ruben Gees
 */
class ChatRoomUsersEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val roomId: String
) : Endpoint<List<ChatRoomUser>> {

    override fun build(): ProxerCall<List<ChatRoomUser>> {
        return internalApi.roomUsers(roomId)
    }
}
