package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available languages of an {@link com.proxerme.library.entitiy.list.MediaListEntry}.
 * <p>
 * This is the same as the {@link MediaLanguage}, but is needed because the APIs encodes both differently.
 *
 * @author Ruben Gees
 */
public enum MediaListEntryLanguage {
    @Json(name = "de")GERMAN,
    @Json(name = "en")ENGLISH,
    @Json(name = "gersub")GERMAN_SUB,
    @Json(name = "gerdub")GERMAN_DUB,
    @Json(name = "engsub")ENGLISH_SUB,
    @Json(name = "engdub")ENGLISH_DUB
}
