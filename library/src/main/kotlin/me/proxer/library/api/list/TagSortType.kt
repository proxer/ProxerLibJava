package me.proxer.library.api.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class TagSortType {

    @Json(name = "ASC")
    ASCENDING,

    @Json(name = "DESC")
    DESCENDING
}
