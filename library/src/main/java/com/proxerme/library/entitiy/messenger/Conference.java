package com.proxerme.library.entitiy.messenger;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

/**
 * Entity representing a single conference. This might be a group chat or a conversation with a
 * single user (indicated by {@link #isGroup()}.
 *
 * @author Ruben Gees
 */
public final class Conference implements IdItem, TimeItem, ImageItem {

    private static final String IMAGE_DELIMITER = ":";
    private static final String EMPTY_RESULT = "";

    @Json(name = "id")
    private String id;
    @Json(name = "topic")
    private String topic;
    @Json(name = "topic_custom")
    private String customTopic;
    @Json(name = "count")
    private int participantAmount;
    @Json(name = "image")
    private String image;
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

    private Conference() {

    }

    /**
     * The constructor.
     *
     * @param id                  The id of this conference.
     * @param topic               The topic.
     * @param customTopic         A custom topic set by a user.
     * @param participantAmount   The amount of participants in this conference.
     * @param imageType           The type of the image. This is "avatar" in most cases.
     * @param imageId             The id of the image.
     * @param group               Indicator if this is a group chat.
     * @param read                Indicator if this conference has been read by the current user.
     * @param time                The time of the last message in this conference.
     * @param unreadMessageAmount The amount of unread messages for the current user.
     * @param lastReadMessageId   The id of the last read message of the current user.
     */
    public Conference(@NotNull final String id, @NotNull final String topic, @NotNull final String customTopic,
                      final int participantAmount, @Nullable final String imageType, @Nullable String imageId,
                      final boolean group, final boolean read, Date time, final int unreadMessageAmount,
                      @NotNull String lastReadMessageId) {
        this.id = id;
        this.topic = topic;
        this.customTopic = customTopic;
        this.participantAmount = participantAmount;
        this.group = group;
        this.read = read;
        this.time = time;
        this.unreadMessageAmount = unreadMessageAmount;
        this.lastReadMessageId = lastReadMessageId;

        if ((imageType == null || imageType.isEmpty()) && (imageId == null || imageId.isEmpty())) {
            this.image = EMPTY_RESULT;
        } else {
            this.image = imageType + IMAGE_DELIMITER + imageId;
        }
    }

    /**
     * Returns the id of the last read message.
     *
     * @return The id.
     */
    @NotNull
    public String getLastReadMessageId() {
        return lastReadMessageId;
    }

    /**
     * Returns the type of the image. Might be empty if there is no image.
     *
     * @return The image type.
     */
    @NotNull
    public String getImageType() {
        if (image.isEmpty()) {
            return EMPTY_RESULT;
        } else {
            int delimiterIndex = image.indexOf(IMAGE_DELIMITER);

            if (delimiterIndex < 0) {
                return EMPTY_RESULT;
            } else {
                return image.substring(0, delimiterIndex);
            }
        }
    }

    /**
     * Returns the id of the image. Might be empty if there is no image.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getImageId() {
        if (image.isEmpty()) {
            return EMPTY_RESULT;
        } else {
            int delimiterIndex = image.indexOf(IMAGE_DELIMITER);

            if (delimiterIndex < 0) {
                return EMPTY_RESULT;
            } else {
                return image.substring(delimiterIndex + 1);
            }
        }
    }

    /**
     * Returns if this message has been read.
     *
     * @return True, if read.
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Returns the custom topic for this conference. Might be empty.
     *
     * @return The custom topic.
     */
    @NotNull
    public String getCustomTopic() {
        return customTopic;
    }

    /**
     * Returns the amount of participants in this conference.
     *
     * @return The amount.
     */
    public int getParticipantAmount() {
        return participantAmount;
    }

    /**
     * Returns the topic of this conference.
     *
     * @return The topic.
     */
    @NotNull
    public String getTopic() {
        return topic;
    }

    /**
     * Returns the id of this conference.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Returns the time of the last message in this conference.
     *
     * @return The time.
     */
    @Override
    @NotNull
    public Date getTime() {
        return time;
    }

    /**
     * Returns if this conference is a group chat.
     *
     * @return True, if it is a group chat.
     */
    public boolean isGroup() {
        return group;
    }

    /**
     * Returns the amount of unread messages.
     *
     * @return The amount of unread messages.
     */
    public int getUnreadMessageAmount() {
        return unreadMessageAmount;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Conference that = (Conference) o;

        if (participantAmount != that.participantAmount) return false;
        if (group != that.group) return false;
        if (read != that.read) return false;
        if (unreadMessageAmount != that.unreadMessageAmount) return false;
        if (!id.equals(that.id)) return false;
        if (!topic.equals(that.topic)) return false;
        if (!customTopic.equals(that.customTopic)) return false;
        if (!image.equals(that.image)) return false;
        if (!time.equals(that.time)) return false;
        return lastReadMessageId.equals(that.lastReadMessageId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + topic.hashCode();
        result = 31 * result + customTopic.hashCode();
        result = 31 * result + participantAmount;
        result = 31 * result + image.hashCode();
        result = 31 * result + (group ? 1 : 0);
        result = 31 * result + (read ? 1 : 0);
        result = 31 * result + time.hashCode();
        result = 31 * result + unreadMessageAmount;
        result = 31 * result + lastReadMessageId.hashCode();
        return result;
    }
}
