package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum MediaListSortCriteria {
    @Json(name = "title")TITLE,
    @Json(name = "clicks")CLICKS,
    @Json(name = "rating")RATING,
}
