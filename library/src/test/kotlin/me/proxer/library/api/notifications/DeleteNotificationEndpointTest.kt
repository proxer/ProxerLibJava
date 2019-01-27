package me.proxer.library.api.notifications

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DeleteNotificationEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        val result = api.notifications
            .deleteNotification("123")
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPathDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.notifications.deleteNotification("123")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/notifications/delete")
    }

    @Test
    fun testParameterDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.notifications.deleteNotification("123")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("nid=123")
    }

    @Test
    fun testPathAll() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.notifications.deleteAllNotifications()
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/notifications/delete")
    }

    @Test
    fun testParameterAll() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.notifications.deleteAllNotifications()
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("nid=0")
    }
}
