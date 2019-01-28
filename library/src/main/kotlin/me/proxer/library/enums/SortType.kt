package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available sort types.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class SortType {

    @Json(name = "ASC")
    ASCENDING,

    @Json(name = "DESC")
    DESCENDING
}
