package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.entity.chat.ChatRoom
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UserChatRoomsEndpointTest : ProxerTest() {

    private val firstExpectedChatRoom = ChatRoom(
        id = "1",
        name = "Proxer.Me Hauptchat",
        topic = "Willkommen im Proxer-Chat!",
        isReadOnly = false
    )

    private val lastExpectedChatRoom = ChatRoom(
        id = "6",
        name = "Chat-Ankündigungen (21.03.18)",
        topic = "",
        isReadOnly = true
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("chat_rooms.json") {
            api.chat
                .userRooms()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo firstExpectedChatRoom
        result.last() shouldBeEqualTo lastExpectedChatRoom
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("chat_rooms.json") {
            api.chat.userRooms()
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/chat/myrooms"
    }
}
