package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Country

/**
 * Entity containing the relevant info of a translator group, associated with an [Entry].
 *
 * @property name The name.
 * @property country The country, the translator group is active in.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class EntryTranslatorGroup(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "country") val country: Country
) : ProxerIdItem
