package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import lombok.AccessLevel;
import lombok.Getter;
import me.proxer.library.entitiy.messenger.Conference;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author Ruben Gees
 */
class ConferenceAdapter {

    @FromJson
    Conference fromJson(IntermediateConference json) {
        return new Conference(json.id, json.topic, json.customTopic, json.participantAmount, json.getImage(),
                json.getImageType(), json.group, json.read, json.date, json.unreadMessageAmount,
                json.lastReadMessageId);
    }

    static class IntermediateConference {

        private static final String IMAGE_DELIMITER = ":";
        private static final String EMPTY_RESULT = "";

        @Json(name = "id")
        String id;

        @Json(name = "topic")
        String topic;

        @Json(name = "topic_custom")
        String customTopic;

        @Json(name = "count")
        int participantAmount;

        @Json(name = "group")
        boolean group;

        @Json(name = "read")
        boolean read;

        @Json(name = "timestamp_end")
        Date date;

        @Json(name = "read_count")
        int unreadMessageAmount;

        @Json(name = "read_mid")
        String lastReadMessageId;

        @Getter(AccessLevel.NONE)
        @Json(name = "image")
        String image;

        @NotNull
        String getImage() {
            final int delimiterIndex = image.indexOf(IMAGE_DELIMITER);

            if (delimiterIndex < 0) {
                return EMPTY_RESULT;
            } else {
                return image.substring(0, delimiterIndex);
            }
        }

        @NotNull
        String getImageType() {
            final int delimiterIndex = image.indexOf(IMAGE_DELIMITER);

            if (delimiterIndex < 0) {
                return EMPTY_RESULT;
            } else {
                return image.substring(delimiterIndex + 1);
            }
        }
    }
}
