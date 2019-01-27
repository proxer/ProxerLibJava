package me.proxer.library.entity.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.api.NumberBasedBoolean
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.TagSubType
import me.proxer.library.enums.TagType

/**
 * Entity representing a single tag.
 *
 * @property type The type.
 * @property name The name.
 * @property description The description.
 * @property subType The sub type.
 * @property isSpoiler If this tag is a spoiler.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Tag(
    @Json(name = "id") override val id: String,
    @Json(name = "type") val type: TagType,
    @Json(name = "tag") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "subtype") val subType: TagSubType,
    @field:NumberBasedBoolean @Json(name = "is_spoiler") val isSpoiler: Boolean
) : ProxerIdItem
