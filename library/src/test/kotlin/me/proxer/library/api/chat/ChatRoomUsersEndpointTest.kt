package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.entity.chat.ChatRoomUser
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ChatRoomUsersEndpointTest : ProxerTest() {

    private val firstExpectedUser = ChatRoomUser(
        id = "730019", name = "Akaya-", image = "730019_0bvh9W.jpg",
        status = "My heart is sinking like a stone, in an ocean of it's own.", isModerator = false
    )

    private val lastExpectedUser = ChatRoomUser(
        id = "229687", name = "Aver", image = "229687_FYkLhC.png", status = "....", isModerator = true
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("chat_room_users.json") {
            api.chat
                .roomUsers("123")
                .build()
                .safeExecute()
        }

        result.first() shouldEqual firstExpectedUser
        result.last() shouldEqual lastExpectedUser
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("chat_room_users.json") {
            api.chat.roomUsers("12")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/chat/roomusers?room_id=12"
    }
}
