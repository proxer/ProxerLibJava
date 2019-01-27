package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum holding the available sort types.
 *
 * @author Ruben Gees
 */
enum class SortType {

    @Json(name = "ASC")
    ASCENDING,

    @Json(name = "DESC")
    DESCENDING
}
