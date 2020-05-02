package me.proxer.library.entity.notifications

import com.squareup.moshi.JsonClass

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
@JsonClass(generateAdapter = true)
data class NotificationInfo(
    val messageAmount: Int,
    val friendRequestAmount: Int,
    val newsAmount: Int,
    val notificationAmount: Int
)
