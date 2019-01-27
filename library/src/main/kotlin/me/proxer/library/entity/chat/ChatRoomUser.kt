package me.proxer.library.entity.chat

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import me.proxer.library.internal.adapter.NumberBasedBoolean

/**
 * Entity representing a user, active in a [ChatRoom].
 *
 * @property name The name.
 * @property status The status.
 * @property isModerator If this user is a moderator.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class ChatRoomUser(
    @Json(name = "uid") override val id: String,
    @Json(name = "username") val name: String,
    @Json(name = "avatar") override val image: String,
    @Json(name = "status") val status: String,
    @field:NumberBasedBoolean @field:Json(name = "mod") val isModerator: Boolean
) : ProxerIdItem, ProxerImageItem
