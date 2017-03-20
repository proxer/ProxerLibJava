package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum for selecting the length bound.
 *
 * @author Ruben Gees
 */
public enum LengthBound {
    @Json(name = "up")HIGHER,
    @Json(name = "down")LOWER
}
