package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import me.proxer.library.entitiy.notifications.Notification;
import me.proxer.library.enums.NotificationType;
import me.proxer.library.util.ProxerUrls;
import okhttp3.HttpUrl;

import java.util.Date;

/**
 * @author Ruben Gees
 */
class NotificationAdapter {

    @FromJson
    Notification fromJson(final IntermediateNotification json) {
        final String base = ProxerUrls.webBase().toString();
        final HttpUrl properContentLink = HttpUrl.parse(base.substring(0, base.length() - 1) + json.contentLink);

        return new Notification(json.id, json.type, json.contentId, properContentLink, json.text,
                json.date, json.additionalDescription);
    }

    static class IntermediateNotification {

        @Json(name = "id")
        String id;

        @Json(name = "type")
        NotificationType type;

        @Json(name = "tid")
        String contentId;

        @Json(name = "link")
        String contentLink;

        @Json(name = "linktext")
        String text;

        @Json(name = "time")
        Date date;

        @Json(name = "description")
        String additionalDescription;
    }
}
