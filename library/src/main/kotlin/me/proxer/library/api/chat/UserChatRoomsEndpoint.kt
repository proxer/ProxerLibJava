package me.proxer.library.api.chat

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.chat.ChatRoom

/**
 * Endpoint for retrieving the used chat rooms of a user.
 * Requires a user to be logged in.
 *
 * @author Ruben Gees
 */
class UserChatRoomsEndpoint internal constructor(private val internalApi: InternalApi) : Endpoint<List<ChatRoom>> {

    override fun build(): ProxerCall<List<ChatRoom>> {
        return internalApi.userRooms()
    }
}
