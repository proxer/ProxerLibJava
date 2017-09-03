package me.proxer.library.enums;

import com.squareup.moshi.Json;
import me.proxer.library.entity.info.Entry;

/**
 * Enum holding the available progress types of an user at a media {@link Entry}.
 *
 * @author Ruben Gees
 */
public enum UserMediaProgress {
    @Json(name = "0") WATCHED,
    @Json(name = "1") WATCHING,
    @Json(name = "2") WILL_WATCH,
    @Json(name = "3") CANCELLED
}
