package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum SynonymType {
    @Json(name = "name")ORIGINAL,
    @Json(name = "nameeng")ENGLISH,
    @Json(name = "nameger")GERMAN,
    @Json(name = "namejap")JAPANESE,
    @Json(name = "syn")ALTERNATIVE,
}
