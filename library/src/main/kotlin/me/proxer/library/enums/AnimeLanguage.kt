package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available languages of an anime.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "OTHER")
enum class AnimeLanguage {

    @Json(name = "gersub")
    GERMAN_SUB,

    @Json(name = "gerdub")
    GERMAN_DUB,

    @Json(name = "engsub")
    ENGLISH_SUB,

    @Json(name = "engdub")
    ENGLISH_DUB,

    @Json(name = "misc")
    OTHER
}
