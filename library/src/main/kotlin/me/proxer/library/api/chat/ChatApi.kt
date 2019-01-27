package me.proxer.library.api.chat

import retrofit2.Retrofit

/**
 * Api for the Chat class.
 *
 * @author Ruben Gees
 */
class ChatApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun publicRooms(): PublicChatRoomsEndpoint {
        return PublicChatRoomsEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun userRooms(): UserChatRoomsEndpoint {
        return UserChatRoomsEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun messages(roomId: String): ChatMessagesEndpoint {
        return ChatMessagesEndpoint(internalApi, roomId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun sendMessage(roomId: String, message: String): SendChatMessageEndpoint {
        return SendChatMessageEndpoint(internalApi, roomId, message)
    }

    /**
     * Returns the respective endpoint.
     */
    fun reportMessage(messageId: String, message: String): ReportChatMessageEndpoint {
        return ReportChatMessageEndpoint(internalApi, messageId, message)
    }

    /**
     * Returns the respective endpoint.
     */
    fun roomUsers(roomId: String): ChatRoomUsersEndpoint {
        return ChatRoomUsersEndpoint(internalApi, roomId)
    }
}
