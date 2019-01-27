package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import java.util.Date

/**
 * Entity representing a single genre in the context of an [Entry].
 *
 * @property entryTagId The id of the associated tag entry.
 * @property name The name.
 * @property description The description.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class InfoGenre(
    @Json(name = "id") override val id: String,
    @Json(name = "tid") val entryTagId: String,
    @Json(name = "timestamp") override val date: Date,
    @Json(name = "tag") val name: String,
    @Json(name = "description") val description: String
) : ProxerIdItem, ProxerDateItem
