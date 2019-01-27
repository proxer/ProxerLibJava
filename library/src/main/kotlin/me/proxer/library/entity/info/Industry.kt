package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Country
import me.proxer.library.enums.IndustryType
import okhttp3.HttpUrl

/**
 * Entity with detailed information concerning an industry.
 *
 * @property name The name.
 * @property type The type.
 * @property country The country, the industry resides in.
 * @property link The link to the homepage.
 * @property description The description.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Industry(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: IndustryType,
    @Json(name = "country") val country: Country,
    @Json(name = "link") val link: HttpUrl?,
    @Json(name = "description") val description: String
) : ProxerIdItem
