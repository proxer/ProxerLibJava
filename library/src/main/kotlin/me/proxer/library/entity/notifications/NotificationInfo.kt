package me.proxer.library.entity.notifications

import com.squareup.moshi.Json

/**
 * Entity holding counts of notification types.
 *
 * @property messageAmount The amount of messages.
 * @property friendRequestAmount The amount of friend requests.
 * @property newsAmount The amount of news.
 * @property notificationAmount The amount of general notifications.
 *
 * @author Ruben Gees
 */
data class NotificationInfo(
    @Json(name = "message_amount") val messageAmount: Int,
    @Json(name = "friend_request_amount") val friendRequestAmount: Int,
    @Json(name = "news_amount") val newsAmount: Int,
    @Json(name = "notification_amount") val notificationAmount: Int
)
