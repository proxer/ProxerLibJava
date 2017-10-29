package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the sub types of a {@link me.proxer.library.entity.list.Tag}.
 *
 * @author Ruben Gees
 */
public enum TagSubType {
    @Json(name = "misc") MISCELLANEOUS,
    @Json(name = "persoenlichkeiten") PERSONALITIES,
    @Json(name = "gefuehle") FEELINGS,
    @Json(name = "zeichnung") DRAWING,
    @Json(name = "uebernatuerliches") SUPERNATURAL,
    @Json(name = "sport") SPORT,
    @Json(name = "menschen") PEOPLE,
    @Json(name = "zukunft") FUTURE,
    @Json(name = "story") STORY,
    @Json(name = "prota") PROTAGONIST
}
