package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonDataException
import me.proxer.library.entity.notifications.NotificationInfo
import org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class NotificationAdapterTest {

    private val adapter = NotificationInfoAdapter()

    @Test
    fun testFromJson() {
        assertThat(adapter.fromJson(intArrayOf(0, 0, 1, 2, 3, 4)))
            .isEqualTo(NotificationInfo(1, 2, 3, 4))
    }

    @Test
    fun testFromJsonInvalidSizeTooSmall() {
        assertThatThrownBy { adapter.fromJson(intArrayOf(1, 2, 3, 4, 5)) }
            .isInstanceOf(JsonDataException::class.java)
    }

    @Test
    fun testFromJsonInvalidSizeTooLarge() {
        assertThatThrownBy { adapter.fromJson(intArrayOf(1, 2, 3, 4, 5, 6, 7)) }
            .isInstanceOf(JsonDataException::class.java)
    }
}
