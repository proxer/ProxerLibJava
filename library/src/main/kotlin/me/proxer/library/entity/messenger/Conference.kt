package me.proxer.library.entity.messenger

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import java.util.Date

/**
 * Entity representing a single conference. This might be a group chat or a conversation with a
 * single user (indicated by [isGroup]).
 *
 * @property topic The topic.
 * @property customTopic The custom topic.
 * @property participantAmount The amount of participants.
 * @property imageType The type of the image.
 * @property isGroup If this is a group.
 * @property isRead If this conference has been read.
 * @property unreadMessageAmount The amount of unread messages.
 * @property unreadMessageAmount The id of the last read message. Can be "0".
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Conference(
    @Json(name = "id") override val id: String,
    @Json(name = "topic") val topic: String,
    @Json(name = "topic_custom") val customTopic: String,
    @Json(name = "count") val participantAmount: Int,
    @Json(name = "image") override val image: String,
    @Json(name = "image_type") val imageType: String,
    @Json(name = "group") val isGroup: Boolean,
    @Json(name = "read") val isRead: Boolean,
    @Json(name = "timestamp_end") override val date: Date,
    @Json(name = "read_count") val unreadMessageAmount: Int,
    @Json(name = "read_mid") val lastReadMessageId: String
) : ProxerIdItem, ProxerDateItem, ProxerImageItem
