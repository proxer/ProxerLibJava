package com.proxerme.library.api;

import com.proxerme.library.entitiy.messenger.ConferenceInfo;
import com.proxerme.library.entitiy.messenger.ConferenceParticipant;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

/**
 * @author Ruben Gees
 */
class ConferenceInfoAdapter {

    @FromJson
    ConferenceInfo fromJson(IntermediateConferenceInfoContainer json) {
        return new ConferenceInfo(json.info.topic, json.info.participantAmount, json.info.firstMessageTime,
                json.info.lastMessageTime, json.info.leaderId, json.participants);
    }

    private static class IntermediateConferenceInfoContainer {

        @Json(name = "conference")
        private IntermediateConferenceInfo info;

        @Json(name = "users")
        private List<ConferenceParticipant> participants;

        private static class IntermediateConferenceInfo {

            @Json(name = "topic")
            private String topic;

            @Json(name = "count")
            private int participantAmount;

            @Json(name = "timestamp_start")
            private Date firstMessageTime;

            @Json(name = "timestamp_end")
            private Date lastMessageTime;

            @Json(name = "leader")
            private String leaderId;
        }
    }
}
