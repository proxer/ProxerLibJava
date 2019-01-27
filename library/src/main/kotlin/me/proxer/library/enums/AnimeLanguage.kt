package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

/**
 * Enum holding the available languages of an anime.
 *
 * @author Ruben Gees
 */
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
