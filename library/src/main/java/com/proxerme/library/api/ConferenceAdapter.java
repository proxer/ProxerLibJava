package com.proxerme.library.api;

import com.proxerme.library.entitiy.messenger.Conference;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author Ruben Gees
 */
class ConferenceAdapter {

    private static final String IMAGE_DELIMITER = ":";
    private static final String EMPTY_RESULT = "";

    @FromJson
    Conference fromJson(IntermediateConference json) {
        return new Conference(json.id, json.topic, json.customTopic, json.participantAmount, json.getImageId(),
                json.getImageType(), json.group, json.read, json.time, json.unreadMessageAmount,
                json.lastReadMessageId);
    }

    private static class IntermediateConference {

        @Json(name = "id")
        private String id;

        @Json(name = "topic")
        private String topic;

        @Json(name = "topic_custom")
        private String customTopic;

        @Json(name = "count")
        private int participantAmount;

        @Json(name = "group")
        private boolean group;

        @Json(name = "read")
        private boolean read;

        @Json(name = "timestamp_end")
        private Date time;

        @Json(name = "read_count")
        private int unreadMessageAmount;

        @Json(name = "read_mid")
        private String lastReadMessageId;

        @Getter(AccessLevel.NONE)
        @Json(name = "image")
        private String image;

        @NotNull
        private String getImageId() {
            final int delimiterIndex = image.indexOf(IMAGE_DELIMITER);

            if (delimiterIndex < 0) {
                return EMPTY_RESULT;
            } else {
                return image.substring(0, delimiterIndex);
            }
        }

        @NotNull
        private String getImageType() {
            final int delimiterIndex = image.indexOf(IMAGE_DELIMITER);

            if (delimiterIndex < 0) {
                return EMPTY_RESULT;
            } else {
                return image.substring(delimiterIndex + 1);
            }
        }
    }
}
