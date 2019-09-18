package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import me.proxer.library.entity.notifications.Notification
import me.proxer.library.enums.NotificationType
import me.proxer.library.util.ProxerUrls
import java.util.Date

/**
 * @author Ruben Gees
 */
internal class NotificationAdapter {

    @FromJson
    fun fromJson(json: IntermediateNotification): Notification {
        val properContentLink = ProxerUrls.webBase.newBuilder()
            .addEncodedPathSegments(
                json.contentLink
                    .trimStart('/')
                    .substringBeforeLast("#")
            )
            .build()

        return Notification(
            json.id, json.type, json.contentId, properContentLink, json.text, json.date, json.additionalDescription
        )
    }

    @ToJson
    fun toJson(value: Notification): IntermediateNotification {
        return IntermediateNotification(
            value.id, value.type, value.contentId, value.contentLink.toString(),
            value.text, value.date, value.additionalDescription
        )
    }

    @JsonClass(generateAdapter = true)
    internal data class IntermediateNotification(
        @Json(name = "id") val id: String,
        @Json(name = "type") val type: NotificationType,
        @Json(name = "tid") val contentId: String,
        @Json(name = "link") val contentLink: String,
        @Json(name = "linktext") val text: String,
        @Json(name = "time") val date: Date,
        @Json(name = "description") val additionalDescription: String
    )
}
