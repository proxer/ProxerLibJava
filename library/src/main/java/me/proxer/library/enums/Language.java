package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * An enum that holds the basic languages.
 *
 * @author Desnoo
 */
public enum Language {
    @Json(name = "de")GERMAN,
    @Json(name = "en")ENGLISH,
    @Json(name = "misc")OTHER,
}
