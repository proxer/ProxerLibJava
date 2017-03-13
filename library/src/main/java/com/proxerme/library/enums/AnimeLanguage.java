package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available languages of an anime.
 *
 * @author Ruben Gees
 */
public enum AnimeLanguage {
    @Json(name = "gersub")GERMAN_SUB,
    @Json(name = "gerdub")GERMAN_DUB,
    @Json(name = "engsub")ENGLISH_SUB,
    @Json(name = "engdub")ENGLISH_DUB
}
