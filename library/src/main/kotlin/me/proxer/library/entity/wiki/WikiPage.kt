package me.proxer.library.entity.wiki

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Entity representing a page of the proxer wiki.
 *
 * @property type The type of the source content. Might be something like "html" or "markdown".
 * @property content The content of the page as html.
 */
@JsonClass(generateAdapter = true)
data class WikiPage(
    @Json(name = "type") val type: String,
    @Json(name = "content") val content: String
)
