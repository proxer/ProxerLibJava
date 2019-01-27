package me.proxer.library.entity.forum

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.api.NumberBasedBoolean
import java.util.Date

/**
 * Class representing a topic in the forum.
 *
 * @property categoryId The id of the category this topic resides in.
 * @property categoryName The name of the category this topic resides in.
 * @property subject The subject of this topic.
 * @property isLocked If this topic is locked.
 * @property postAmount The amount of posts in this topic.
 * @property hits The hits of this topic.
 * @property firstPostDate The date of the first post in this topic.
 * @property lastPostDate The date of the last post in this topic.
 * @property posts The posts in this topic.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Topic(
    @Json(name = "category_id") val categoryId: String,
    @Json(name = "category_name") val categoryName: String,
    @Json(name = "subject") val subject: String,
    @field:NumberBasedBoolean @Json(name = "locked") val isLocked: Boolean,
    @Json(name = "post_count") val postAmount: Int,
    @Json(name = "hits") val hits: Int,
    @Json(name = "first_post_time") val firstPostDate: Date,
    @Json(name = "last_post_time") val lastPostDate: Date,
    @Json(name = "posts") val posts: List<Post>
)
