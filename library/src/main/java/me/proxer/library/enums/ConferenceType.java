package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available conference types.
 *
 * @author Ruben Gees
 */
public enum ConferenceType {
    @Json(name = "favour")FAVORITE,
    @Json(name = "block")BLOCKED,
    @Json(name = "group")GROUP,
    @Json(name = "default")DEFAULT
}
