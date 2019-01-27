package me.proxer.library.internal.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.messenger.ConferenceInfo
import me.proxer.library.entity.messenger.ConferenceParticipant

import java.util.Date

/**
 * @author Ruben Gees
 */
internal class ConferenceInfoAdapter {

    @FromJson
    fun fromJson(json: IntermediateConferenceInfoContainer): ConferenceInfo {
        return ConferenceInfo(
            json.info.topic, json.info.participantAmount, json.info.firstMessageTime,
            json.info.lastMessageTime, json.info.leaderId, json.participants
        )
    }

    @JsonClass(generateAdapter = true)
    internal data class IntermediateConferenceInfoContainer(
        @Json(name = "conference") internal val info: IntermediateConferenceInfo,
        @Json(name = "users") internal val participants: List<ConferenceParticipant>
    ) {

        @JsonClass(generateAdapter = true)
        internal data class IntermediateConferenceInfo(
            @Json(name = "topic") val topic: String,
            @Json(name = "count") val participantAmount: Int,
            @Json(name = "timestamp_start") val firstMessageTime: Date,
            @Json(name = "timestamp_end") val lastMessageTime: Date,
            @Json(name = "leader") val leaderId: String
        )
    }
}
