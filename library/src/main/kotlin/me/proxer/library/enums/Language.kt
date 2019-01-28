package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * An enum that holds the basic languages.
 *
 * @author Desnoo
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "OTHER")
enum class Language {

    @Json(name = "de")
    GERMAN,

    @Json(name = "en")
    ENGLISH,

    @Json(name = "misc")
    OTHER
}
