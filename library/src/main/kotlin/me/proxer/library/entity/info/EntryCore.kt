package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.api.DelimitedEnumSet
import me.proxer.library.api.DelimitedStringSet
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Category
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.License
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium

/**
 * Entity holding the detail data of an Entry (Anime, Manga). These are only the basics.
 * For full info, consult [Entry].
 *
 * @property name The name.
 * @property genres The genres.
 * @property fskConstraints The fsk ratings.
 * @property description The description.
 * @property medium The medium.
 * @property episodeAmount The amount of episodes, this entry has. They do not necessarily have to be uploaded.
 * @property state The current state.
 * @property ratingSum The sum of all ratings.
 * @property ratingAmount The clicks on this entry, in this season.
 * @property category The category.
 * @property license The type of license.
 * @property adaptionInfo Information regarding the adaption of this entry.
 *
 * @author Desnoo
 */
@JsonClass(generateAdapter = true)
data class EntryCore(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @field:DelimitedStringSet(valuesToKeep = ["Slice of Life"]) @Json(name = "genre") val genres: Set<String>,
    @field:DelimitedEnumSet @Json(name = "fsk") val fskConstraints: Set<FskConstraint>,
    @Json(name = "description") val description: String,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "count") val episodeAmount: Int,
    @Json(name = "state") val state: MediaState,
    @Json(name = "rate_sum") val ratingSum: Int,
    @Json(name = "rate_count") val ratingAmount: Int,
    @Json(name = "clicks") val clicks: Int,
    @Json(name = "kat") val category: Category,
    @Json(name = "license") val license: License,
    @Json(name = "adaption_data") val adaptionInfo: AdaptionInfo
) : ProxerIdItem {

    /**
     * Returns the average of all ratings.
     */
    val rating = when {
        ratingAmount <= 0 -> 0f
        else -> ratingSum.toFloat() / ratingAmount.toFloat()
    }
}
