package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonDataException
import me.proxer.library.entity.notifications.NotificationInfo
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class NotificationAdapterTest {

    private val adapter = NotificationInfoAdapter()

    @Test
    fun testFromJson() {
        adapter.fromJson(intArrayOf(0, 0, 1, 2, 3, 4)) shouldBeEqualTo NotificationInfo(
            messageAmount = 1,
            friendRequestAmount = 2,
            newsAmount = 3,
            notificationAmount = 4
        )
    }

    @Test
    fun testFromJsonInvalidSizeTooSmall() {
        invoking { adapter.fromJson(intArrayOf(1, 2, 3, 4, 5)) } shouldThrow JsonDataException::class
    }

    @Test
    fun testFromJsonInvalidSizeTooLarge() {
        invoking { adapter.fromJson(intArrayOf(1, 2, 3, 4, 5, 6, 7)) } shouldThrow JsonDataException::class
    }
}
