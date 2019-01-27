package me.proxer.library.entity.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.internal.adapter.DelimitedEnumSet
import me.proxer.library.internal.adapter.DelimitedStringSet

/**
 * Entity holding all relevant info about a single entry in the media list.
 *
 * @property name The name.
 * @property genres The genres.
 * @property medium The medium.
 * @property episodeAmount The amount of episodes, this entry has. They do not necessarily have to be uploaded.
 * @property state The current state.
 * @property ratingSum The sum of all ratings.
 * @property ratingAmount The clicks on this entry, in this season.
 * @property languages The languages, this entry is available in.
 *
 * @author Desnoo
 */
@JsonClass(generateAdapter = true)
data class MediaListEntry(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @field:DelimitedStringSet(valuesToKeep = ["Slice of Life"]) @Json(name = "genre") val genres: Set<String>,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "count") val episodeAmount: Int,
    @Json(name = "state") val state: MediaState,
    @Json(name = "rate_sum") val ratingSum: Int,
    @Json(name = "rate_count") val ratingAmount: Int,
    @field:DelimitedEnumSet(delimiter = ",") @Json(name = "language") val languages: Set<MediaLanguage>
) : ProxerIdItem {

    /**
     * Returns the average of all ratings.
     */
    val rating = when {
        ratingAmount <= 0 -> 0f
        else -> ratingSum.toFloat() / ratingAmount.toFloat()
    }
}
