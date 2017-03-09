package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available progress types of an user at a media {@link com.proxerme.library.entitiy.info.Entry}.
 *
 * @author Ruben Gees
 */
public enum UserMediaProgress {
    @Json(name = "0")WATCHED,
    @Json(name = "1")WATCHING,
    @Json(name = "2")WILL_WATCH,
    @Json(name = "3")CANCELLED
}
