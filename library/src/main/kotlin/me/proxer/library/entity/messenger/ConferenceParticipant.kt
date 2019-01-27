package me.proxer.library.entity.messenger

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem

/**
 * Entity that represents a participant in a [Conference].
 *
 * @property username The username.
 * @property status The current status.
 *
 * @author Desnoo
 */
@JsonClass(generateAdapter = true)
data class ConferenceParticipant(
    @Json(name = "uid") override val id: String,
    @Json(name = "avatar") override val image: String,
    @Json(name = "username") val username: String,
    @Json(name = "status") val status: String
) : ProxerIdItem, ProxerImageItem
