package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available mediums.
 *
 * @author Ruben Gees
 */
public enum Medium {
    @Json(name = "animeseries") ANIMESERIES,
    @Json(name = "movie") MOVIE,
    @Json(name = "ova") OVA,
    @Json(name = "hentai") HENTAI,
    @Json(name = "mangaseries") MANGASERIES,
    @Json(name = "oneshot") ONESHOT,
    @Json(name = "doujin") DOUJIN,
    @Json(name = "hmanga") HMANGA
}
