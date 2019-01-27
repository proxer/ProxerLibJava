package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum for filtering rated tags.
 *
 * @author Ruben Gees
 */
enum class TagRateFilter {

    @Json(name = "rate_1")
    RATED_ONLY,

    @Json(name = "rate_10")
    ALL
}
