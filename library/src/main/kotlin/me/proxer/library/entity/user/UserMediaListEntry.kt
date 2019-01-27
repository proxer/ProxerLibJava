package me.proxer.library.entity.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.UserMediaProgress

/**
 * Entity holding all relevant information of an entry in the user's media list (watched, watching).
 * This also includes comments by the user.
 *
 * @property name The name.
 * @property episodeAmount The amount of episodes.
 * @property medium The medium.
 * @property state The state, the associated [me.proxer.library.entity.info.Entry] currently has.
 * @property commentId The id of the associated [me.proxer.library.entity.info.Comment].
 * @property commentContent The content of the associated [me.proxer.library.entity.info.Comment].
 * @property mediaProgress The progress, the user has made on this entry.
 * @property episode The episode, the user is currently at.
 * @property rating The rating, the user has given. 0 means that the user has not rated yet.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class UserMediaListEntry(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "count") val episodeAmount: Int,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "estate") val state: MediaState,
    @Json(name = "cid") val commentId: String,
    @Json(name = "comment") val commentContent: String,
    @Json(name = "state") val mediaProgress: UserMediaProgress,
    @Json(name = "episode") val episode: Int,
    @Json(name = "rating") val rating: Int
) : ProxerIdItem
