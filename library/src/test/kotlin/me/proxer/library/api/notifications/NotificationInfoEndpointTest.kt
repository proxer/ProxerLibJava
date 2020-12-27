package me.proxer.library.api.notifications

import me.proxer.library.ProxerTest
import me.proxer.library.entity.notifications.NotificationInfo
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class NotificationInfoEndpointTest : ProxerTest() {

    private val expectedInfo = NotificationInfo(
        messageAmount = 3,
        friendRequestAmount = 0,
        newsAmount = 238,
        notificationAmount = 93
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("notification_info.json") {
            api.notifications
                .notificationInfo()
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedInfo
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("notification_info.json") {
            api.notifications
                .notificationInfo()
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/notifications/count"
    }
}
