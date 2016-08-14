package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;

/**
 * Class representing a single conferenceInfo. This might be a group chat or a conversation with a
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

    @Body(name = "id")
    private String id;
    @Body(name = "topic")
    private String topic;
    @Body(name = "topic_custom")
    private String customTopic;
    @Body(name = "count")
    private int participantAmount;
    @Body(name = "image")
    private String image;
    @Body(name = "group")
    private boolean isGroup;
    @Body(name = "read")
    private boolean isRead;
    @Body(name = "timestamp_end")
    private long time;
    @Body(name = "read_count")
    private int unreadMessageAmount;
    @Body(name = "read_mid")
    private String lastReadMessageId;

    Conference() {
    }

    /**
     * The constructor.
     *
     * @param id                  The id of this conferenceInfo.
     * @param topic               The topic.
     * @param customTopic         A custom topic set by a user.
     * @param participantAmount   The amount of participants in this conferenceInfo.
     * @param imageType           The type of the image. This is "avatar" in most cases.
     * @param imageId             The id of the image.
     * @param isGroup             Indicator if this is a group chat.
     * @param isRead              Indicator if this conferenceInfo has been read by the current user.
     * @param time                The time of the last message in this conferenceInfo.
     * @param unreadMessageAmount The amount of unread messages for the current user.
     * @param lastReadMessageId   The id of the last read message of the current user.
     */
    public Conference(@NonNull String id, @NonNull String topic, @NonNull String customTopic,
                      @IntRange(from = 2) int participantAmount, @Nullable String imageType,
                      @Nullable String imageId, boolean isGroup, boolean isRead, long time,
                      @IntRange(from = 0) int unreadMessageAmount,
                      @NonNull String lastReadMessageId) {
        this.id = id;
        this.topic = topic;
        this.customTopic = customTopic;
        this.participantAmount = participantAmount;
        this.isGroup = isGroup;
        this.isRead = isRead;
        this.time = time;
        this.unreadMessageAmount = unreadMessageAmount;
        this.lastReadMessageId = lastReadMessageId;

        if (imageType == null || imageType.isEmpty() || imageId == null || imageType.isEmpty()) {
            this.image = "";
        } else {
            this.image = imageType + ":" + imageId;
        }
    }

    protected Conference(Parcel in) {
        this.lastReadMessageId = in.readString();
        this.image = in.readString();
        this.isRead = in.readByte() != 0;
        this.customTopic = in.readString();
        this.participantAmount = in.readInt();
        this.topic = in.readString();
        this.id = in.readString();
        this.time = in.readLong();
        this.isGroup = in.readByte() != 0;
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
        String[] split = image.split(":");

        if (split.length == 2) {
            return split[0];
        } else {
            return "";
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
        String[] split = image.split(":");

        if (split.length == 2) {
            return split[1];
        } else {
            return "";
        }
    }

    /**
     * Returns if this message has been read.
     *
     * @return True, if read.
     */
    public boolean isRead() {
        return isRead;
    }

    /**
     * Returns the custom topic for this conferenceInfo. Might be empty.
     *
     * @return The custom topic.
     */
    @NonNull
    public String getCustomTopic() {
        return customTopic;
    }

    /**
     * Returns the amount of participants in this conferenceInfo.
     *
     * @return The amount.
     */
    @IntRange(from = 2)
    public int getParticipantAmount() {
        return participantAmount;
    }

    /**
     * Returns the topic of this conferenceInfo.
     *
     * @return The topic.
     */
    @NonNull
    public String getTopic() {
        return topic;
    }

    /**
     * Returns the id of this conferenceInfo.
     *
     * @return The id.
     */
    @Override
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the time of the last message in this conferenceInfo.
     *
     * @return The time.
     */
    @Override
    public long getTime() {
        return time;
    }

    /**
     * Returns if this conferenceInfo is a group chat.
     *
     * @return True, if it is a group chat.
     */
    public boolean isGroup() {
        return isGroup;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        if (isRead != that.isRead) return false;
        if (participantAmount != that.participantAmount) return false;
        if (time != that.time) return false;
        if (isGroup != that.isGroup) return false;
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
        result = 31 * result + (isRead ? 1 : 0);
        result = 31 * result + customTopic.hashCode();
        result = 31 * result + participantAmount;
        result = 31 * result + topic.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (isGroup ? 1 : 0);
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
        dest.writeByte(this.isRead ? (byte) 1 : (byte) 0);
        dest.writeString(this.customTopic);
        dest.writeInt(this.participantAmount);
        dest.writeString(this.topic);
        dest.writeString(this.id);
        dest.writeLong(this.time);
        dest.writeByte(this.isGroup ? (byte) 1 : (byte) 0);
        dest.writeInt(this.unreadMessageAmount);
    }
}
