package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available synonym types.
 *
 * @author Ruben Gees
 */
public enum SynonymType {
    @Json(name = "name")ORIGINAL,
    @Json(name = "nameeng")ENGLISH,
    @Json(name = "nameger")GERMAN,
    @Json(name = "namejap")JAPANESE,
    @Json(name = "syn")ORIGINAL_ALTERNATIVE,
}
