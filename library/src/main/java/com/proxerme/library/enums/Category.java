package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available categories.
 *
 * @author Ruben Gees
 */
public enum Category {
    @Json(name = "anime")ANIME,
    @Json(name = "manga")MANGA
}
