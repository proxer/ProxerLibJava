package me.proxer.library.entity.notifications

import com.squareup.moshi.Json
import me.proxer.library.entity.ProxerDateItem
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.NotificationType
import okhttp3.HttpUrl
import java.util.Date

/**
 * Entity representing a single notification.
 * This can be for example a new anime episode or a new forum topic.
 *
 * @property type The type of the notification.
 * @property contentId The id of the content. This depends on the [type].
 * @property contentLink The link to the content.
 * @property text The description of the content. This is directly usable for presentation.
 * @property additionalDescription Additional info, depending on the [type].
 *
 * @author Ruben Gees
 */
data class Notification(
    @Json(name = "id") override val id: String,
    @Json(name = "type") val type: NotificationType,
    @Json(name = "tid") val contentId: String,
    @Json(name = "link") val contentLink: HttpUrl,
    @Json(name = "linktext") val text: String,
    @Json(name = "time") override val date: Date,
    @Json(name = "description") val additionalDescription: String?
) : ProxerIdItem, ProxerDateItem
