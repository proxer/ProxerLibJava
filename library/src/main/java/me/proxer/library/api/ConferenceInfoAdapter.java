package me.proxer.library.api;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import me.proxer.library.entitiy.messenger.ConferenceInfo;
import me.proxer.library.entitiy.messenger.ConferenceParticipant;

import java.util.Date;
import java.util.List;

/**
 * @author Ruben Gees
 */
class ConferenceInfoAdapter {

    @FromJson
    ConferenceInfo fromJson(final IntermediateConferenceInfoContainer json) {
        return new ConferenceInfo(json.info.topic, json.info.participantAmount, json.info.firstMessageTime,
                json.info.lastMessageTime, json.info.leaderId, json.participants);
    }

    private static class IntermediateConferenceInfoContainer {

        @Json(name = "conference")
        IntermediateConferenceInfo info;

        @Json(name = "users")
        List<ConferenceParticipant> participants;

        static class IntermediateConferenceInfo {

            @Json(name = "topic")
            String topic;

            @Json(name = "count")
            int participantAmount;

            @Json(name = "timestamp_start")
            Date firstMessageTime;

            @Json(name = "timestamp_end")
            Date lastMessageTime;

            @Json(name = "leader")
            String leaderId;
        }
    }
}
