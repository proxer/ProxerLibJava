package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ConferenceModificationEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("conference_modification.json")))

        val result = api.messenger
            .markConferenceAsRead("123")
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPathRead() {
        server.enqueue(MockResponse().setBody(fromResource("conference_modification.json")))

        api.messenger.markConferenceAsRead("123")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/messenger/setread")
    }

    @Test
    fun testPathUnread() {
        server.enqueue(MockResponse().setBody(fromResource("conference_modification.json")))

        api.messenger.unmarkConferenceAsRead("123")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/messenger/setunread")
    }

    @Test
    fun testPathBlock() {
        server.enqueue(MockResponse().setBody(fromResource("conference_modification.json")))

        api.messenger.markConferenceAsBlocked("123")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/messenger/setblock")
    }

    @Test
    fun testPathUnblock() {
        server.enqueue(MockResponse().setBody(fromResource("conference_modification.json")))

        api.messenger.unmarkConferenceAsBlocked("123")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/messenger/setunblock")
    }

    @Test
    fun testPathFavour() {
        server.enqueue(MockResponse().setBody(fromResource("conference_modification.json")))

        api.messenger.markConferenceAsFavorite("123")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/messenger/setfavour")
    }

    @Test
    fun testPathUnfavour() {
        server.enqueue(MockResponse().setBody(fromResource("conference_modification.json")))

        api.messenger.unmarkConferenceAsFavorite("123")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/messenger/setunfavour")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("conference_modification.json")))

        api.messenger.markConferenceAsRead("321")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("conference_id=321")
    }
}
