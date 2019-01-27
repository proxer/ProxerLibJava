package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import me.proxer.library.entity.notifications.NotificationInfo

/**
 * @author Ruben Gees
 */
internal class NotificationInfoAdapter {

    private companion object {
        private const val FIELD_AMOUNT = 6

        private const val MESSAGE_FIELD_LOCATION = 2
        private const val FRIEND_REQUEST_FIELD_LOCATION = 3
        private const val NEWS_FIELD_LOCATION = 4
        private const val NOTIFICATIONS_FIELD_LOCATION = 5
    }

    @FromJson
    fun fromJson(json: IntArray): NotificationInfo {
        if (json.size != FIELD_AMOUNT) {
            throw JsonDataException("Invalid json array size: " + json.size)
        }

        return NotificationInfo(
            json[MESSAGE_FIELD_LOCATION], json[FRIEND_REQUEST_FIELD_LOCATION],
            json[NEWS_FIELD_LOCATION], json[NOTIFICATIONS_FIELD_LOCATION]
        )
    }
}
