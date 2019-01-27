package me.proxer.library.entity.chat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.api.NumberBasedBoolean
import me.proxer.library.entity.ProxerIdItem

/**
 * Entity representing a chat room.
 *
 * @property name The name.
 * @property topic the topic.
 * @property isReadOnly if this chat room is read only.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class ChatRoom(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "topic") val topic: String,
    @field:NumberBasedBoolean @Json(name = "flag_readonly") val isReadOnly: Boolean
) : ProxerIdItem
