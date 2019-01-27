package me.proxer.library.api.chat

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.chat.ChatRoom

/**
 * Endpoint for retrieving the public chat rooms.
 *
 * @author Ruben Gees
 */
class PublicChatRoomsEndpoint internal constructor(private val internalApi: InternalApi) : Endpoint<List<ChatRoom>> {

    override fun build(): ProxerCall<List<ChatRoom>> {
        return internalApi.publicRooms()
    }
}
