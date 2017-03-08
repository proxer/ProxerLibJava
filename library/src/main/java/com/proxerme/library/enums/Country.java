package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum Country {
    @Json(name = "de")GERMANY,
    @Json(name = "en")ENGLAND,
    @Json(name = "us")UNITED_STATES,
    @Json(name = "jp")JAPAN,
    @Json(name = "misc")OTHER,
}
