package me.proxer.library.entity.forum

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import java.util.Date

/**
 * Class representing a single post of a forum topic.
 *
 * @property parentId The id of the parent post.
 * @property userId The id of the user.
 * @property username The name of the user.
 * @property signature The signature of the user.
 * @property modifiedById The id of the last user which modified this post.
 * @property modifiedByName The name of the last user which modified this post.
 * @property modifiedDate The date of the last modification.
 * @property modifiedReason The reason of the last modification.
 * @property message The actual text of this post.
 * @property thankYouAmount The amount of given "thank you".
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "id") override val id: String,
    @Json(name = "pid") val parentId: String,
    @Json(name = "uid") val userId: String,
    @Json(name = "username") val username: String,
    @Json(name = "avatar") override val image: String,
    @Json(name = "time") override val date: Date,
    @Json(name = "signature") val signature: String?,
    @Json(name = "modified_by") val modifiedById: String?,
    @Json(name = "modified_name") val modifiedByName: String?,
    @Json(name = "modified_time") val modifiedDate: Date?,
    @Json(name = "modified_reason") val modifiedReason: String?,
    @Json(name = "message") val message: String,
    @Json(name = "thank_you_count") val thankYouAmount: Int
) : ProxerIdItem, ProxerImageItem, ProxerDateItem
