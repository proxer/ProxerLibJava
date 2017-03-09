package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available sort options for comments.
 *
 * @author Ruben Gees
 */
public enum CommentSortCriteria {
    @Json(name = "rating")RATING,
    @Json(name = "")TIME
}
