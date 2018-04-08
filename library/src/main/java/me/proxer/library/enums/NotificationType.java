package me.proxer.library.enums;

import com.serjltt.moshi.adapters.FallbackEnum;
import com.squareup.moshi.Json;

/**
 * Enum holding the available notification types.
 *
 * @author Ruben Gees
 */
@FallbackEnum(name = "OTHER")
public enum NotificationType {
    @Json(name = "user_boardmessage") BOARD_MESSAGE,
    @Json(name = "user_boardreply") BOARD_REPLY,
    @Json(name = "user_friendaccept") FRIEND_ACCEPT,
    @Json(name = "subs_projectstate") SUBS_PROJECT_STATE,
    @Json(name = "media_reminder") REMINDER,
    @Json(name = "ticket") TICKET,
    @Json(name = "ticket_comment") TICKET_COMMENT,
    @Json(name = "ticket_mention") TICKET_MENTION,
    @Json(name = "forum_post") FORUM_POST,
    @Json(name = "forum_topic") FORUM_TOPIC,
    @Json(name = "gallery2_album") GALLERY_ALBUM,
    @Json(name = "apps_release") APPS_RELEASE,
    @Json(name = "apps_state") APPS_STATE,
    @Json(name = "podcast") PODCAST,
    @Json(name = "") OTHER
}
