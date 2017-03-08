package com.proxerme.library.entitiy.messenger;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity representing a single conference. This might be a group chat or a conversation with a
 * single user (indicated by {@link #isGroup()}.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class Conference implements IdItem, TimeItem, ImageItem {

    /**
     * @return The id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * @return The topic.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "topic")
    private String topic;

    /**
     * @return The custom topic.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "topic_custom")
    private String customTopic;

    /**
     * @return The amount of participants.
     */
    @Json(name = "count")
    private int participantAmount;

    /**
     * @return The id of the image.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    private String imageId;

    /**
     * @return The type of the image.
     */
    @Getter(onMethod = @__({@NotNull}))
    private String imageType;

    /**
     * @return The group.
     */
    @Json(name = "group")
    private boolean group;

    /**
     * @return True, if this conference has been read.
     */
    @Json(name = "read")
    private boolean read;

    /**
     * @return The time.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "timestamp_end")
    private Date time;

    /**
     * @return The amount of unread messages.
     */
    @Json(name = "read_count")
    private int unreadMessageAmount;

    /**
     * @return The id of the last read message. Can be "0".
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "read_mid")
    private String lastReadMessageId;
}
