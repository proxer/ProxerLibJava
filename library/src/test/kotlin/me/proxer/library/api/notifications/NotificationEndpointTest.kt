package me.proxer.library.api.notifications

import me.proxer.library.ProxerTest
import me.proxer.library.entity.notifications.Notification
import me.proxer.library.enums.NotificationFilter
import me.proxer.library.enums.NotificationType
import me.proxer.library.fromResource
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class NotificationEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("notification.json")))

        val result = api.notifications
            .notifications()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestNotification())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("notification.json")))

        api.notifications.notifications()
            .page(0)
            .limit(10)
            .markAsRead(true)
            .filter(NotificationFilter.UNREAD)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/notifications/notifications?p=0&limit=10&set_read=true&filter=1")
    }

    private fun buildTestNotification(): Notification {
        return Notification(
            "10185686", NotificationType.REMINDER, "49815105", HttpUrl.get("https://proxer.me/chapter/2373/26/en"),
            "Lesezeichen: <u>Test123 Manga #26</u> ist online!", Date(1494452692L * 1000L), ""
        )
    }
}
