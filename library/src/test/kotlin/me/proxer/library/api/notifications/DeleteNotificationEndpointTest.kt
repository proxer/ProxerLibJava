package me.proxer.library.api.notifications

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DeleteNotificationEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.notifications
                .deleteNotification("123")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPathDefault() {
        val (_, request) = server.runRequest("empty.json") {
            api.notifications.deleteNotification("123")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/notifications/delete"
    }

    @Test
    fun testParameterDefault() {
        val (_, request) = server.runRequest("empty.json") {
            api.notifications.deleteNotification("123")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "nid=123"
    }

    @Test
    fun testPathAll() {
        val (_, request) = server.runRequest("empty.json") {
            api.notifications.deleteAllNotifications()
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/notifications/delete"
    }

    @Test
    fun testParameterAll() {
        val (_, request) = server.runRequest("empty.json") {
            api.notifications.deleteAllNotifications()
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "nid=0"
    }
}
