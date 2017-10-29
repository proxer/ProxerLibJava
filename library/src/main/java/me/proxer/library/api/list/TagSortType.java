package me.proxer.library.api.list;

import com.squareup.moshi.Json;

/**
 * @author Ruben Gees
 */
enum TagSortType {
    @Json(name = "ASC") ASCENDING,
    @Json(name = "DESC") DESCENDING
}
