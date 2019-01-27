package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.SynonymType

/**
 * Entity holding the synonyms of an entry name.
 *
 * @property entryId The id of the associated entry.
 * @property type The type.
 * @property name The name.
 *
 * @author Desnoo
 */
@JsonClass(generateAdapter = true)
data class Synonym(
    @Json(name = "id") override val id: String,
    @Json(name = "eid") val entryId: String,
    @Json(name = "type") val type: SynonymType,
    @Json(name = "name") val name: String
) : ProxerIdItem
