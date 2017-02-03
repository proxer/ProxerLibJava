package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;

/**
 * Entity representing a single conference. This might be a group chat or a conversation with a
 * single user (indicated by {@link #isGroup()}.
 *
 * @author Ruben Gees
 */
public class Conference implements Parcelable, IdItem, TimeItem, ImageItem {

    public static final Parcelable.Creator<Conference> CREATOR = new Parcelable.Creator<Conference>() {
        @Override
        public Conference createFromParcel(Parcel source) {
            return new Conference(source);
        }

        @Override
        public Conference[] newArray(int size) {
            return new Conference[size];
        }
    };

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
    private long time;
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
    public Conference(@NonNull String id, @NonNull String topic, @NonNull String customTopic,
                      @IntRange(from = 2) int participantAmount, @Nullable String imageType,
                      @Nullable String imageId, boolean group, boolean read, long time,
                      @IntRange(from = 0) int unreadMessageAmount,
                      @NonNull String lastReadMessageId) {
        this.id = id;
        this.topic = topic;
        this.customTopic = customTopic;
        this.participantAmount = participantAmount;
        this.group = group;
        this.read = read;
        this.time = time;
        this.unreadMessageAmount = unreadMessageAmount;
        this.lastReadMessageId = lastReadMessageId;
        this.image = imageType + IMAGE_DELIMITER + imageId;
    }

    protected Conference(Parcel in) {
        this.lastReadMessageId = in.readString();
        this.image = in.readString();
        this.read = in.readByte() != 0;
        this.customTopic = in.readString();
        this.participantAmount = in.readInt();
        this.topic = in.readString();
        this.id = in.readString();
        this.time = in.readLong();
        this.group = in.readByte() != 0;
        this.unreadMessageAmount = in.readInt();
    }

    /**
     * Returns the id of the last read message.
     *
     * @return The id.
     */
    @NonNull
    public String getLastReadMessageId() {
        return lastReadMessageId;
    }

    /**
     * Returns the type of the image. Might be empty if there is no image.
     *
     * @return The image type.
     */
    @NonNull
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
    @NonNull
    public String getImageId() {
        if (image.isEmpty()) {
            return EMPTY_RESULT;
        } else {
            int delimiterIndex = image.indexOf(IMAGE_DELIMITER);

            if (delimiterIndex < 0) {
                return EMPTY_RESULT;
            } else {
                return image.substring(delimiterIndex);
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
    @NonNull
    public String getCustomTopic() {
        return customTopic;
    }

    /**
     * Returns the amount of participants in this conference.
     *
     * @return The amount.
     */
    @IntRange(from = 2)
    public int getParticipantAmount() {
        return participantAmount;
    }

    /**
     * Returns the topic of this conference.
     *
     * @return The topic.
     */
    @NonNull
    public String getTopic() {
        return topic;
    }

    /**
     * Returns the id of this conference.
     *
     * @return The id.
     */
    @Override
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the time of the last message in this conference.
     *
     * @return The time.
     */
    @Override
    public long getTime() {
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
    @IntRange(from = 0)
    public int getUnreadMessageAmount() {
        return unreadMessageAmount;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        if (read != that.read) return false;
        if (participantAmount != that.participantAmount) return false;
        if (time != that.time) return false;
        if (group != that.group) return false;
        if (unreadMessageAmount != that.unreadMessageAmount) return false;
        if (!lastReadMessageId.equals(that.lastReadMessageId)) return false;
        if (!image.equals(that.image)) return false;
        if (!customTopic.equals(that.customTopic)) return false;
        if (!topic.equals(that.topic)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = lastReadMessageId.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + (read ? 1 : 0);
        result = 31 * result + customTopic.hashCode();
        result = 31 * result + participantAmount;
        result = 31 * result + topic.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (group ? 1 : 0);
        result = 31 * result + unreadMessageAmount;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lastReadMessageId);
        dest.writeString(this.image);
        dest.writeByte(this.read ? (byte) 1 : (byte) 0);
        dest.writeString(this.customTopic);
        dest.writeInt(this.participantAmount);
        dest.writeString(this.topic);
        dest.writeString(this.id);
        dest.writeLong(this.time);
        dest.writeByte(this.group ? (byte) 1 : (byte) 0);
        dest.writeInt(this.unreadMessageAmount);
    }
}
