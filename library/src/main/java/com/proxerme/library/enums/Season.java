package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum Season {
    @Json(name = "0")UNSPECIFIED_ALT,
    @Json(name = "1")WINTER,
    @Json(name = "2")SPRING,
    @Json(name = "3")SUMMER,
    @Json(name = "4")AUTUMN,
    @Json(name = "5")UNSPECIFIED
}
