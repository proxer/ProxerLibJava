package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class Conference implements Parcelable {

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
    @Body(name = "read_mid")
    private String lastReadMessageId;
    @Body(name = "image")
    private String imageId;
    @Body(name = "read")
    private boolean isRead;
    @Body(name = "topic_custom")
    private String customTopic;
    @Body(name = "count")
    private int participantAmount;
    @Body(name = "topic")
    private String topic;
    @Body(name = "id")
    private String id;
    @Body(name = "timestamp_end")
    private long time;
    @Body(name = "group")
    private boolean isGroup;
    @Body(name = "read_count")
    private int unreadMessageAmount;

    Conference() {
    }

    public Conference(@NonNull String lastReadMessageId, @NonNull String imageId, boolean isRead,
                      @NonNull String customTopic, int participantAmount, @NonNull String topic,
                      @NonNull String id, long time, boolean isGroup, int unreadMessageAmount) {
        this.lastReadMessageId = lastReadMessageId;
        this.imageId = imageId;
        this.isRead = isRead;
        this.customTopic = customTopic;
        this.participantAmount = participantAmount;
        this.topic = topic;
        this.id = id;
        this.time = time;
        this.isGroup = isGroup;
        this.unreadMessageAmount = unreadMessageAmount;
    }

    protected Conference(Parcel in) {
        this.lastReadMessageId = in.readString();
        this.imageId = in.readString();
        this.isRead = in.readByte() != 0;
        this.customTopic = in.readString();
        this.participantAmount = in.readInt();
        this.topic = in.readString();
        this.id = in.readString();
        this.time = in.readLong();
        this.isGroup = in.readByte() != 0;
        this.unreadMessageAmount = in.readInt();
    }

    @NonNull
    public String getLastReadMessageId() {
        return lastReadMessageId;
    }

    @NonNull
    public String getImageId() {
        return imageId;
    }

    public boolean isRead() {
        return isRead;
    }

    @NonNull
    public String getCustomTopic() {
        return customTopic;
    }

    @IntRange(from = 2)
    public int getParticipantAmount() {
        return participantAmount;
    }

    @NonNull
    public String getTopic() {
        return topic;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public boolean isGroup() {
        return isGroup;
    }

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
        if (!imageId.equals(that.imageId)) return false;
        if (!customTopic.equals(that.customTopic)) return false;
        if (!topic.equals(that.topic)) return false;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = lastReadMessageId.hashCode();
        result = 31 * result + imageId.hashCode();
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
        dest.writeString(this.imageId);
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
