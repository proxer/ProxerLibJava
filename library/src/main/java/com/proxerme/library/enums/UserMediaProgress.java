package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum UserMediaProgress {
    @Json(name = "0")WATCHED,
    @Json(name = "1")WATCHING,
    @Json(name = "2")WILL_WATCH,
    @Json(name = "3")CANCELLED
}
