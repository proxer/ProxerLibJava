package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available stats of an {@link com.proxerme.library.entitiy.info.Entry}.
 *
 * @author Ruben Gees
 */
public enum MediaState {
    @Json(name = "0")PRE_AIRING,
    @Json(name = "1")FINISHED,
    @Json(name = "2")AIRING,
    @Json(name = "3")CANCELLED,
    @Json(name = "4")CANCELLED_SUB
}
