package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.api.NumberBasedBoolean
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import java.util.Date

/**
 * Entity representing a single tag in the context of an [Entry].
 *
 * @property entryTagId The id of the associated tag entry.
 * @property isRated If this tag has been rated.
 * @property isSpoiler If this tag is a spoiler.
 * @property name The name.
 * @property description The description.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class InfoTag(
    @Json(name = "id") override val id: String,
    @Json(name = "tid") val entryTagId: String,
    @Json(name = "timestamp") override val date: Date,
    @field:NumberBasedBoolean @Json(name = "rate_flag") val isRated: Boolean,
    @field:NumberBasedBoolean @Json(name = "spoiler_flag") val isSpoiler: Boolean,
    @Json(name = "tag") val name: String,
    @Json(name = "description") val description: String
) : ProxerIdItem, ProxerDateItem
