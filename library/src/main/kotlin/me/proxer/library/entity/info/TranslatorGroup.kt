package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import me.proxer.library.enums.Country
import okhttp3.HttpUrl

/**
 * Entity with detailed information concerning a translator group.
 *
 * @property name The name.
 * @property country The country, the translator group is active in.
 * @property link The link to the homepage.
 * @property description The description.
 * @property clicks The clicks.
 * @property projectAmount The amount of projects.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class TranslatorGroup(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "country") val country: Country,
    @Json(name = "image") override val image: String,
    @Json(name = "link") val link: HttpUrl?,
    @Json(name = "description") val description: String,
    @Json(name = "count") val clicks: Int,
    @Json(name = "cprojects") val projectAmount: Int
) : ProxerIdItem, ProxerImageItem
