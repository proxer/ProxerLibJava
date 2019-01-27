package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.enums.MediaLanguage

/**
 * Entity holding the data of a single manga chapter.
 *
 * @property title The title of the chapter. Can be empty.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class MangaEpisode(
    @Json(name = "no") override val number: Int,
    @Json(name = "typ") override val language: MediaLanguage,
    @Json(name = "title") val title: String
) : Episode(number, language)
