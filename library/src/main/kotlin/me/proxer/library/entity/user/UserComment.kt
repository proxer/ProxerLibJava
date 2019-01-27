package me.proxer.library.entity.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import me.proxer.library.entity.info.Entry
import me.proxer.library.entity.info.RatingDetails
import me.proxer.library.enums.Category
import me.proxer.library.enums.Medium
import me.proxer.library.enums.UserMediaProgress
import java.util.Date

/**
 * The complete details of a comment associated with an [Entry].
 *
 * @property entryId The id of the associated entry.
 * @property entryName The name of the associated entry.
 * @property medium The medium of the associated entry.
 * @property category The category of the associated entry.
 * @property authorId The id of the author.
 * @property mediaProgress The progress, the user has made on the associated media.
 * @property ratingDetails Finer grained ratings for e.g. music.
 * @property content The actual content of the comment.
 * @property overallRating The overall rating.
 * @property episode The episode, the user is currently at.
 * @property helpfulVotes The amount of helpful votes by other users.
 * @property helpfulVotes The username of the author.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class UserComment(
    @Json(name = "id") override val id: String,
    @Json(name = "tid") val entryId: String,
    @Json(name = "name") val entryName: String,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "kat") val category: Category,
    @Json(name = "uid") val authorId: String,
    @Json(name = "state") val mediaProgress: UserMediaProgress,
    @Json(name = "data") val ratingDetails: RatingDetails,
    @Json(name = "comment") val content: String,
    @Json(name = "rating") val overallRating: Int,
    @Json(name = "episode") val episode: Int,
    @Json(name = "positive") val helpfulVotes: Int,
    @Json(name = "timestamp") override val date: Date,
    @Json(name = "username") val author: String,
    @Json(name = "avatar") override val image: String
) : ProxerIdItem, ProxerImageItem, ProxerDateItem
