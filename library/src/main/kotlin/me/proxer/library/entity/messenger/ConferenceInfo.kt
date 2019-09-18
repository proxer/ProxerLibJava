package me.proxer.library.entity.messenger

import com.squareup.moshi.Json
import java.util.Date

/**
 * Entity that holds various info of a [Conference].
 *
 * @property topic The topic.
 * @property participantAmount The amount of participants.
 * @property firstMessageTime The time, the first message was sent.
 * @property lastMessageTime The time, the last message was sent.
 * @property leaderId The id of the conference leader.
 * @property participants The participants of the conference.
 *
 * @author Desnoo
 */
data class ConferenceInfo(
    @Json(name = "topic") val topic: String,
    @Json(name = "count") val participantAmount: Int,
    @Json(name = "timestamp_start") val firstMessageTime: Date,
    @Json(name = "timestamp_end") val lastMessageTime: Date,
    @Json(name = "leader") val leaderId: String,
    @Json(name = "users") val participants: List<ConferenceParticipant>
)
