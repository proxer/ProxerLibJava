package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.entity.chat.ChatRoomUser
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ChatRoomUsersEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("chat_room_users.json")))

        val result = api.chat
            .roomUsers("123")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildFirstUser())
        assertThat(result).last().isEqualTo(buildLastUser())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("chat_room_users.json")))

        api.chat.roomUsers("12")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/chat/roomusers?room_id=12")
    }

    private fun buildFirstUser(): ChatRoomUser {
        return ChatRoomUser(
            "730019", "Akaya-", "730019_0bvh9W.jpg",
            "My heart is sinking like a stone, in an ocean of it's own.", false
        )
    }

    private fun buildLastUser(): ChatRoomUser {
        return ChatRoomUser("229687", "Aver", "229687_FYkLhC.png", "....", true)
    }
}
