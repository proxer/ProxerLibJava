package me.proxer.library.enums;

import com.squareup.moshi.Json;

public enum NotificationFilter {
    @Json(name = "0") ALL,
    @Json(name = "1") UNREAD,
    @Json(name = "2") READ
}
