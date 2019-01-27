package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

/**
 * An enum that holds the basic languages.
 *
 * @author Desnoo
 */
@FallbackEnum(name = "OTHER")
enum class Language {

    @Json(name = "de")
    GERMAN,

    @Json(name = "en")
    ENGLISH,

    @Json(name = "misc")
    OTHER
}
