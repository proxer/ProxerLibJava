package me.proxer.library.entity.notifications

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import java.util.Date

/**
 * Entity holding all relevant info of a single news article.
 *
 * @property description The description.
 * @property image The image id.
 * @property subject The subject.
 * @property hits The amount of clicks by users.
 * @property threadId The thread id.
 * @property authorId The id of the author.
 * @property author The username of the author.
 * @property commentAmount The amount of comments.
 * @property categoryId The id of the category.
 * @property category The name of the category.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class NewsArticle(
    @Json(name = "nid") override val id: String,
    @Json(name = "time") override val date: Date,
    @Json(name = "description") val description: String,
    @Json(name = "image_id") override val image: String,
    @Json(name = "subject") val subject: String,
    @Json(name = "hits") val hits: Int = 0,
    @Json(name = "thread") val threadId: String,
    @Json(name = "uid") val authorId: String,
    @Json(name = "uname") val author: String,
    @Json(name = "posts") val commentAmount: Int = 0,
    @Json(name = "catid") val categoryId: String,
    @Json(name = "catname") val category: String
) : ProxerIdItem, ProxerDateItem, ProxerImageItem
