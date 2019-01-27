package me.proxer.library.api.notifications

import me.proxer.library.ProxerTest
import me.proxer.library.entity.notifications.NotificationInfo
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class NotificationInfoEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("notification_info.json")))

        val result = api.notifications
            .notificationInfo()
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestNotificationInfo())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("notification_info.json")))

        api.notifications
            .notificationInfo()
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/notifications/count")
    }

    private fun buildTestNotificationInfo(): NotificationInfo {
        return NotificationInfo(3, 0, 238, 93)
    }
}
