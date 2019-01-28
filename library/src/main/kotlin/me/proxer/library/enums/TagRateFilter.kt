package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum for filtering rated tags.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class TagRateFilter {

    @Json(name = "rate_1")
    RATED_ONLY,

    @Json(name = "rate_10")
    ALL
}
