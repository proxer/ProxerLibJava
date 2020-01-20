package me.proxer.library.api.notifications

import me.proxer.library.ProxerTest
import me.proxer.library.entity.notifications.Notification
import me.proxer.library.enums.NotificationFilter
import me.proxer.library.enums.NotificationType
import me.proxer.library.runRequest
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class NotificationEndpointTest : ProxerTest() {

    private val expectedNotification = Notification(
        id = "10185686", type = NotificationType.REMINDER, contentId = "49815105",
        contentLink = "https://proxer.me/chapter/2373/26/en".toHttpUrl(),
        text = "Lesezeichen: <u>Test123 Manga #26</u> ist online!",
        date = Date(1494452692L * 1000L), additionalDescription = ""
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("notification.json") {
            api.notifications
                .notifications()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo expectedNotification
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("notification.json") {
            api.notifications.notifications()
                .page(0)
                .limit(10)
                .markAsRead(true)
                .filter(NotificationFilter.UNREAD)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/notifications/notifications?p=0&limit=10&set_read=true&filter=1"
    }
}
