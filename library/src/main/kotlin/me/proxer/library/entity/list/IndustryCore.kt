package me.proxer.library.entity.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Country
import me.proxer.library.enums.IndustryType

/**
 * Entity containing the core information of an industry.
 *
 * @property name The name.
 * @property type The type.
 * @property country The country, the industry resides in.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class IndustryCore(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: IndustryType,
    @Json(name = "country") val country: Country
) : ProxerIdItem
