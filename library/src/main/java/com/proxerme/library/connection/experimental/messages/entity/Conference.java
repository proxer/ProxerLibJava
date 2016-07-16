package com.proxerme.library.connection.experimental.messages.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.interfaces.TimeItem;

/**
 * Entity holding all relevant information about a Conference.
 *
 * @author Ruben Gees
 */
public class Conference implements Parcelable, IdItem, TimeItem, ImageItem {

    public static final Parcelable.Creator<Conference> CREATOR = new Parcelable.Creator<Conference>() {
        public Conference createFromParcel(Parcel source) {
            return new Conference(source);
        }

        public Conference[] newArray(int size) {
            return new Conference[size];
        }
    };

    @Body(name = "id")
    String id;
    @Body(name = "topic")
    String topic;
    @Body(name = "count")
    int participantAmount;
    @Body(name = "conference")
    boolean isConference;
    @Body(name = "timestamp_end")
    long time;
    @Body(name = "read")
    byte isRead;
    @Body(name = "image")
    String imageId;

    Conference() {

    }

    /**
     * @param id                The id of the Conference.
     * @param topic             The topic.
     * @param participantAmount The amount of participants.
     * @param isConference        Indicator, if this Conference has more than 2 participants.
     *                          (group conversation)
     * @param time              The time of the last message.
     * @param isRead              Indicator, if the user has isRead the last message in this Conference.
     * @param imageId           The id of the image.
     */
    public Conference(@NonNull String id, @NonNull String topic, int participantAmount,
                      boolean isConference, long time, boolean isRead, @NonNull String imageId) {
        this.id = id;
        this.topic = topic;
        this.participantAmount = participantAmount;
        this.isConference = isConference;
        this.time = time;
        this.isRead = (byte) (isRead ? 1 : 0);
        this.imageId = imageId;
    }

    protected Conference(Parcel in) {
        this.id = in.readString();
        this.topic = in.readString();
        this.participantAmount = in.readInt();
        this.isConference = in.readByte() != 0;
        this.time = in.readLong();
        this.isRead = in.readByte();
        this.imageId = in.readString();
    }

    /**
     * Returns the id of this Conference.
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the topic of this Conference.
     * @return The topic.
     */
    @NonNull
    public String getTopic() {
        return topic;
    }

    /**
     * Returns the amount of participants in this Conference.
     * @return The amount of participants.
     */
    @IntRange(from = 2)
    public int getParticipantAmount() {
        return participantAmount;
    }

    /**
     * Returns if this Conference is a group conversation.
     * @return Returns true if this isConference has more than 2 participants.
     */
    public boolean isConference() {
        return isConference;
    }

    /**
     * Returns the time of the last message.
     * @return The time.
     */
    @Override
    public long getTime() {
        return time;
    }

    /**
     * Returns if the user has isRead the last message in this isConference.
     * @return True if the user has isRead the last message.
     */
    public boolean isRead() {
        return isRead == 1;
    }

    /**
     * Returns the id of this image of the Conference. May be and empty string, if this isConference
     * has no image. (Users without a profile image and group conversation)
     * @return The id.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conference that = (Conference) o;

        if (participantAmount != that.participantAmount) return false;
        if (isConference != that.isConference) return false;
        if (time != that.time) return false;
        if (isRead != that.isRead) return false;
        if (!id.equals(that.id)) return false;
        if (!topic.equals(that.topic)) return false;
        return imageId.equals(that.imageId);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + topic.hashCode();
        result = 31 * result + participantAmount;
        result = 31 * result + (isConference ? 1 : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (int) isRead;
        result = 31 * result + imageId.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.topic);
        dest.writeInt(this.participantAmount);
        dest.writeByte(isConference ? (byte) 1 : (byte) 0);
        dest.writeLong(this.time);
        dest.writeByte(this.isRead);
        dest.writeString(this.imageId);
    }
}
