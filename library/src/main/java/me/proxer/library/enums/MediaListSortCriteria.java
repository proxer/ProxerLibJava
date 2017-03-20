package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available sort criteria for the media list.
 *
 * @author Ruben Gees
 */
public enum MediaListSortCriteria {
    @Json(name = "title")TITLE,
    @Json(name = "clicks")CLICKS,
    @Json(name = "rating")RATING,
}
