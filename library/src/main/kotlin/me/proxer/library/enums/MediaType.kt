package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum of the available media types. Similar to [Medium].
 *
 * @author Desnoo
 */
enum class MediaType {

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

    @Json(name = "oneshot")
    ONESHOT,

    @Json(name = "doujin")
    DOUJIN,

    @Json(name = "hmanga")
    HMANGA,

    @Json(name = "all-anime")
    ALL_ANIME,

    @Json(name = "all-manga")
    ALL_MANGA,

    @Json(name = "all")
    ALL,

    @Json(name = "all18")
    ALL_WITH_HENTAI
}
