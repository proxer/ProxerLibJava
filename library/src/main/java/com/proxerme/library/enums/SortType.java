package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available sort types.
 *
 * @author Ruben Gees
 */
public enum SortType {
    @Json(name = "ASC")ASCENDING,
    @Json(name = "DESC")DESCENDING
}
