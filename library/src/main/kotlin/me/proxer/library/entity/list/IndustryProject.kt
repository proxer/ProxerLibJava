package me.proxer.library.entity.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.IndustryType
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.internal.adapter.DelimitedEnumSet
import me.proxer.library.internal.adapter.DelimitedStringSet

/**
 * Entity holding all relevant info about a single entry in an industry's project list.
 *
 * @property name The name.
 * @property genres The genres.
 * @property fskConstraints The fsk ratings.
 * @property medium The medium.
 * @property industryType The type of the industry.
 * @property state The current state.
 * @property ratingSum The sum of all ratings.
 * @property ratingAmount The clicks on this entry, in this season.
 *
 * @author Desnoo
 */
@JsonClass(generateAdapter = true)
data class IndustryProject(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @field:DelimitedStringSet(valuesToKeep = ["Slice of Life"]) @Json(name = "genre") val genres: Set<String>,
    @field:DelimitedEnumSet @Json(name = "fsk") val fskConstraints: Set<FskConstraint>,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "type") val industryType: IndustryType,
    @Json(name = "state") val state: MediaState,
    @Json(name = "rate_sum") val ratingSum: Int,
    @Json(name = "rate_count") val ratingAmount: Int
) : ProxerIdItem {

    /**
     * Returns the average of all ratings.
     */
    val rating = when {
        ratingAmount <= 0 -> 0f
        else -> ratingSum.toFloat() / ratingAmount.toFloat()
    }
}
