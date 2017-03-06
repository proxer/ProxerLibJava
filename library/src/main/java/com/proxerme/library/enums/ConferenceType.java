package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum ConferenceType {
    @Json(name = "favour")FAVORITE,
    @Json(name = "block")BLOCKED,
    @Json(name = "group")GROUP,
    @Json(name = "default")DEFAULT
}
