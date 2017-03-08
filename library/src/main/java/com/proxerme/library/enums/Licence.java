package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Create class
 *
 * @author Ruben Gees
 */
public enum Licence {
    @Json(name = "0")UNKNOWN,
    @Json(name = "1")NOT_LICENSED,
    @Json(name = "2")LICENSED
}
