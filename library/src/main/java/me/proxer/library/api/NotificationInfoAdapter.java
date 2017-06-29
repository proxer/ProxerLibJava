package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import me.proxer.library.entitiy.notifications.NotificationInfo;

/**
 * @author Ruben Gees
 */
class NotificationInfoAdapter {

    @FromJson
    NotificationInfo fromJson(final int[] json) {
        if (json.length != 6) {
            throw new JsonDataException("Invalid json array size: " + json.length);
        }

        return new NotificationInfo(json[2], json[3], json[4], json[5]);
    }
}
