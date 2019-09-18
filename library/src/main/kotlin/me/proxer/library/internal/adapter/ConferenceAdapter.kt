package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import me.proxer.library.entity.messenger.Conference
import java.util.Date

/**
 * @author Ruben Gees
 */
internal class ConferenceAdapter {

    private companion object {
        private const val IMAGE_DELIMITER = ":"
    }

    @FromJson
    fun fromJson(json: IntermediateConference): Conference {
        return Conference(
            json.id, json.topic, json.customTopic, json.participantAmount, json.image, json.imageType,
            json.isGroup, json.isRead, json.date, json.unreadMessageAmount, json.lastReadMessageId
        )
    }

    @ToJson
    fun toJson(value: Conference): IntermediateConference {
        return IntermediateConference(
            id = value.id,
            topic = value.topic,
            customTopic = value.customTopic,
            participantAmount = value.participantAmount,
            isGroup = value.isGroup,
            isRead = value.isRead,
            date = value.date,
            unreadMessageAmount = value.unreadMessageAmount,
            lastReadMessageId = value.lastReadMessageId,
            rawImage = value.imageType + IMAGE_DELIMITER + value.image
        )
    }

    @JsonClass(generateAdapter = true)
    internal data class IntermediateConference(
        @Json(name = "id") val id: String,
        @Json(name = "topic") val topic: String,
        @Json(name = "topic_custom") val customTopic: String,
        @Json(name = "count") val participantAmount: Int,
        @Json(name = "group") val isGroup: Boolean,
        @Json(name = "read") val isRead: Boolean,
        @Json(name = "timestamp_end") val date: Date,
        @Json(name = "read_count") val unreadMessageAmount: Int,
        @Json(name = "read_mid") val lastReadMessageId: String,
        @Json(name = "image") val rawImage: String
    ) {

        val imageType = rawImage.substringBefore(IMAGE_DELIMITER, "")
        val image = rawImage.substringAfter(IMAGE_DELIMITER, "")
    }
}
