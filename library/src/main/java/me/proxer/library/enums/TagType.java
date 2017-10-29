package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the types of a {@link me.proxer.library.entity.list.Tag}.
 *
 * @author Ruben Gees
 */
public enum TagType {
    @Json(name = "entry_genre") GENRE,
    @Json(name = "entry_tag") TAG,
    @Json(name = "entry_tag_h") H_TAG
}
