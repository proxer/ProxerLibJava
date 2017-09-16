package me.proxer.library.entity.messenger;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

/**
 * Entity that holds various info of a {@link Conference}.
 *
 * @author Desnoo
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class ConferenceInfo {

    /**
     * Returns the topic.
     */
    @Json(name = "topic")
    private String topic;

    /**
     * Returns the amount of participants.
     */
    @Json(name = "count")
    private int participantAmount;

    /**
     * Returns the time, the first message was sent.
     */
    @Json(name = "timestamp_start")
    private Date firstMessageTime;

    /**
     * Returns the time, the last message was sent.
     */
    @Json(name = "timestamp_end")
    private Date lastMessageTime;

    /**
     * Returns the id of the conference leader.
     */
    @Json(name = "leader")
    private String leaderId;

    /**
     * Returns the participants of the conference.
     */
    @Json(name = "users")
    private List<ConferenceParticipant> participants;
}
