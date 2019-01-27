package me.proxer.library.entity.manga

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Entity representing a single page from a [Chapter].
 *
 * @property name The name of the page. To be used for retrieving the url.
 * @property height The height of the page.
 * @property width The width of the page.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Page(
    @Json(name = "name") val name: String,
    @Json(name = "height") val height: Int,
    @Json(name = "width") val width: Int
)
