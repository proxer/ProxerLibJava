package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.entity.chat.ChatRoom
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class PublicChatRoomsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("chat_rooms.json")))

        val result = api.chat
            .publicRooms()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildFirstTestRoom())
        assertThat(result).last().isEqualTo(buildLastTestRoom())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("chat_rooms.json")))

        api.chat.publicRooms()
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/chat/publicrooms")
    }

    private fun buildFirstTestRoom(): ChatRoom {
        return ChatRoom("1", "Proxer.Me Hauptchat", "Willkommen im Proxer-Chat!", false)
    }

    private fun buildLastTestRoom(): ChatRoom {
        return ChatRoom("6", "Chat-Ankündigungen (21.03.18)", "", true)
    }
}
