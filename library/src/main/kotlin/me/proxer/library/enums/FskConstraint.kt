package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available fsk ratings and types.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class FskConstraint {

    @Json(name = "fsk0")
    FSK_0,

    @Json(name = "fsk6")
    FSK_6,

    @Json(name = "fsk12")
    FSK_12,

    @Json(name = "fsk16")
    FSK_16,

    @Json(name = "fsk18")
    FSK_18,

    @Json(name = "bad_language")
    BAD_LANGUAGE,

    @Json(name = "violence")
    VIOLENCE,

    @Json(name = "fear")
    FEAR,

    @Json(name = "sex")
    SEX
}
