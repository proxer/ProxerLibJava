package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available licence types.
 *
 * @author Ruben Gees
 */
public enum Licence {
    @Json(name = "0")UNKNOWN,
    @Json(name = "1")NOT_LICENSED,
    @Json(name = "2")LICENSED
}
