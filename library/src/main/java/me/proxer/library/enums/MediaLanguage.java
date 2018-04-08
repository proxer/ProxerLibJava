package me.proxer.library.enums;

import com.serjltt.moshi.adapters.FallbackEnum;
import com.squareup.moshi.Json;
import me.proxer.library.entity.info.Entry;

/**
 * Enum holding the available languages of an {@link Entry}.
 *
 * @author Ruben Gees
 */
@FallbackEnum(name = "OTHER")
public enum MediaLanguage {
    @Json(name = "de") GERMAN,
    @Json(name = "en") ENGLISH,
    @Json(name = "gersub") GERMAN_SUB,
    @Json(name = "gerdub") GERMAN_DUB,
    @Json(name = "engsub") ENGLISH_SUB,
    @Json(name = "engdub") ENGLISH_DUB,
    @Json(name = "misc") OTHER,
}
