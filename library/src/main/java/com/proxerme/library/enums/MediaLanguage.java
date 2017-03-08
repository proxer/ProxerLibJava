package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum MediaLanguage {
    @Json(name = "de")GERMAN,
    @Json(name = "en")ENGLISH,
    @Json(name = "gersub")GERMAN_SUB,
    @Json(name = "gerdub")GERMAN_DUB,
    @Json(name = "engsub")ENGLISH_SUB,
    @Json(name = "engdub")ENGLISH_DUB
}
