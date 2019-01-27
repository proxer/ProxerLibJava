package me.proxer.library.entity.media

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.api.DelimitedStringSet
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.CalendarDay
import java.util.Date

/**
 * Entity holding the data of a single calendar entry.
 *
 * @property entryId The id of the associated [me.proxer.library.entity.info.Entry].
 * @property name The name.
 * @property episode The next episode to be aired.
 * @property episodeTitle The title of the next episode to be aired. May be empty.
 * @property timezone The timezone of the country, the episode is aired in.
 * @property industryId The id of the television channel, transmitting the episode. "0" if not set.
 * @property industryName The name of the television channel, transmitting the episode. May be null.
 * @property weekDay The day of the week, the episode is aired.
 * @property uploadDate The date (and time), the episode will be uploaded.
 * This is just an estimated value and can be imprecise.
 * @property genres The genres.
 * @property ratingSum The sum of all ratings.
 * @property ratingAmount The amount of ratings.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class CalendarEntry(
    @Json(name = "id") override val id: String,
    @Json(name = "eid") val entryId: String,
    @Json(name = "entryname") val name: String,
    @Json(name = "episode") val episode: Int,
    @Json(name = "episodeTitle") val episodeTitle: String,
    @Json(name = "time") override val date: Date,
    @Json(name = "timezone") val timezone: String,
    @Json(name = "iid") val industryId: String,
    @Json(name = "industryname") val industryName: String?,
    @Json(name = "weekday") val weekDay: CalendarDay,
    @Json(name = "uptime") val uploadDate: Date,
    @field:DelimitedStringSet(valuesToKeep = ["Slice of Life"]) @Json(name = "genre") val genres: Set<String>,
    @Json(name = "rate_sum") val ratingSum: Int,
    @Json(name = "rate_count") val ratingAmount: Int
) : ProxerIdItem, ProxerDateItem {

    /**
     * Returns the average of all ratings.
     */
    val rating = when {
        ratingAmount <= 0 -> 0f
        else -> ratingSum.toFloat() / ratingAmount.toFloat()
    }
}
