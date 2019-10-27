package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available mediums.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class Medium {

    @Json(name = "animeseries")
    ANIMESERIES,

    @Json(name = "movie")
    MOVIE,

    @Json(name = "ova")
    OVA,

    @Json(name = "hentai")
    HENTAI,

    @Json(name = "mangaseries")
    MANGASERIES,

    @Json(name = "lightnovel")
    LIGHTNOVEL,

    @Json(name = "webnovel")
    WEBNOVEL,

    @Json(name = "oneshot")
    ONESHOT,

    @Json(name = "doujin")
    DOUJIN,

    @Json(name = "hmanga")
    HMANGA,

    @Json(name = "")
    OTHER
}
