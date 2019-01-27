package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import java.util.Date

/**
 * Entity containing meta information about a single forum discussion.
 *
 * @property categoryId The id of the category this topic resides in.
 * @property categoryName The name of the category this topic resides in.
 * @property subject The subject of this topic.
 * @property postAmount The amount of posts in this topic.
 * @property hits The hits of this topic.
 * @property firstPostDate The date of the first post in this topic.
 * @property firstPostUserId The user id of the author of the first post.
 * @property firstPostUsername The username of the author of the first post.
 * @property lastPostDate The date of the last post in this topic.
 * @property lastPostUserId The user id of the author of the last post.
 * @property lastPostUsername The username of the author of the last post.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class ForumDiscussion(
    @Json(name = "id") override val id: String,
    @Json(name = "category_id") val categoryId: String,
    @Json(name = "category_name") val categoryName: String,
    @Json(name = "subject") val subject: String,
    @Json(name = "posts") val postAmount: Int,
    @Json(name = "hits") val hits: Int,
    @Json(name = "first_post_time") val firstPostDate: Date,
    @Json(name = "first_post_userid") val firstPostUserId: String,
    @Json(name = "first_post_guest_name") val firstPostUsername: String,
    @Json(name = "last_post_time") val lastPostDate: Date,
    @Json(name = "last_post_userid") val lastPostUserId: String,
    @Json(name = "last_post_guest_name") val lastPostUsername: String
) : ProxerIdItem
