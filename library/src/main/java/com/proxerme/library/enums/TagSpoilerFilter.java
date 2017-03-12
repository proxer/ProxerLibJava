package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum for filtering tags with spoilers.
 *
 * @author Ruben Gees
 */
public enum TagSpoilerFilter {
    @Json(name = "spoiler_0")NO_SPOILERS,
    @Json(name = "spoiler_10")ALL,
    @Json(name = "spoiler_1")ONLY_SPOILERS
}
