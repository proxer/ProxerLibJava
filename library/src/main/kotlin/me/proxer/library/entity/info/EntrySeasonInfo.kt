package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Season

/**
 * Entity holding the season of an entry.
 *
 * @property year The year.
 * @property season The actual season.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class EntrySeasonInfo(
    @Json(name = "id") override val id: String,
    @Json(name = "year") val year: Int,
    @Json(name = "season") val season: Season
) : ProxerIdItem
