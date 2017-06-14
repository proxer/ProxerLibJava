package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the possible filter options for the user media list.
 *
 * @author Ruben Gees
 */
public enum UserMediaListFilterType {
    @Json(name = "stateFilter0")WATCHED,
    @Json(name = "stateFilter1")WATCHING,
    @Json(name = "stateFilter2")WILL_WATCH,
    @Json(name = "stateFilter3")CANCELLED
}
