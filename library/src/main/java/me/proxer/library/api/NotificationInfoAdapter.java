package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;
import me.proxer.library.entitiy.notifications.NotificationInfo;

/**
 * @author Ruben Gees
 */
class NotificationInfoAdapter {

    private static final int FIELD_AMOUNT = 6;

    private static final int MESSAGE_FIELD_LOCATION = 2;
    private static final int FRIEND_REQUEST_FIELD_LOCATION = 3;
    private static final int NEWS_FIELD_LOCATION = 4;
    private static final int NOTIFICATIONS_FIELD_LOCATION = 5;

    @FromJson
    NotificationInfo fromJson(final int[] json) {
        if (json.length != FIELD_AMOUNT) {
            throw new JsonDataException("Invalid json array size: " + json.length);
        }

        return new NotificationInfo(json[MESSAGE_FIELD_LOCATION], json[FRIEND_REQUEST_FIELD_LOCATION],
                json[NEWS_FIELD_LOCATION], json[NOTIFICATIONS_FIELD_LOCATION]);
    }
}
