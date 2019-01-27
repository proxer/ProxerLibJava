package me.proxer.library.entity.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import me.proxer.library.enums.Country

/**
 * Entity containing the core information of a translator group.
 *
 * @property name The name.
 * @property country The country, the translator group is active in.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class TranslatorGroupCore(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "country") val country: Country,
    @Json(name = "image") override val image: String
) : ProxerIdItem, ProxerImageItem
