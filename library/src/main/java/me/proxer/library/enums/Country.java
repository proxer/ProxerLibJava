package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available country.
 *
 * @author Ruben Gees
 */
public enum Country {
    @Json(name = "de")GERMANY,
    @Json(name = "en")ENGLAND,
    @Json(name = "us")UNITED_STATES,
    @Json(name = "jp")JAPAN,
    @Json(name = "kr")KOREA,
    @Json(name = "misc")OTHER,
}
