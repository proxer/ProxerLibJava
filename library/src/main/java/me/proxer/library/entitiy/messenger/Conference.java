package me.proxer.library.entitiy.messenger;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * Entity representing a single conference. This might be a group chat or a conversation with a
 * single user (indicated by {@link #isGroup()}.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class Conference implements ProxerIdItem, ProxerDateItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the topic.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "topic")
    private String topic;

    /**
     * Returns the custom topic.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "topic_custom")
    private String customTopic;

    /**
     * Returns the amount of participants.
     */
    @Json(name = "count")
    private int participantAmount;

    /**
     * Returns the id of the image.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    private String image;

    /**
     * Returns the type of the image.
     */
    @Getter(onMethod = @__({@Nonnull}))
    private String imageType;

    /**
     * Returns the group.
     */
    @Json(name = "group")
    private boolean group;

    /**
     * Returns true, if this conference has been read.
     */
    @Json(name = "read")
    private boolean read;

    /**
     * Returns the time.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "timestamp_end")
    private Date date;

    /**
     * Returns the amount of unread messages.
     */
    @Json(name = "read_count")
    private int unreadMessageAmount;

    /**
     * Returns the id of the last read message. Can be "0".
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "read_mid")
    private String lastReadMessageId;
}
