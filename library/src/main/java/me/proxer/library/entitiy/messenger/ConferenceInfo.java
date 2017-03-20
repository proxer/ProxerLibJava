package me.proxer.library.entitiy.messenger;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

/**
 * Entity that holds various info of a {@link Conference}.
 *
 * @author Desnoo
 */
@Value
public class ConferenceInfo {

    /**
     * Returns the topic.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "topic")
    private String topic;

    /**
     * Returns the amount of participants.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "count")
    private int participantAmount;

    /**
     * Returns the time, the first message was sent.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "timestamp_start")
    private Date firstMessageTime;

    /**
     * Returns the time, the last message was sent.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "timestamp_end")
    private Date lastMessageTime;

    /**
     * Returns the id of the conference leader.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "leader")
    private String leaderId;

    /**
     * Returns the participants of the conference.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "users")
    private List<ConferenceParticipant> participants;
}
