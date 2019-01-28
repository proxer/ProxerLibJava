package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.info.Entry

/**
 * Enum holding the available languages of an [Entry].
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "OTHER")
enum class MediaLanguage {

    @Json(name = "de")
    GERMAN,

    @Json(name = "en")
    ENGLISH,

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
