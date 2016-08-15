package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;

/**
 * The class that represents the ConferenceInfo entity.
 *
 * @author Desnoo
 */
public class ConferenceInfo implements Parcelable {

    public static final Creator<ConferenceInfo> CREATOR = new Creator<ConferenceInfo>() {
        @Override
        public ConferenceInfo createFromParcel(Parcel in) {
            return new ConferenceInfo(in);
        }

        @Override
        public ConferenceInfo[] newArray(int size) {
            return new ConferenceInfo[size];
        }
    };

    @Body(name = "topic")
    String topic;
    @Body(name = "count")
    int participants;
    @Body(name = "timestampStart")
    long firstMessageTime;
    @Body(name = "timestampEnd")
    long lastMessageTime;
    @Body(name = "leader")
    String leaderId;

    /**
     * Private Constructor.
     */
    ConferenceInfo() {
    }

    /**
     * The Constructor.
     *
     * @param topic            The topic.
     * @param participants     The amount of participants in this conference.
     * @param firstMessageTime The time when the conference started.
     * @param lastMessageTime  The time when the last message of the conference was sent.
     * @param leaderId         The user id of the conference leader.
     */
    public ConferenceInfo(@NonNull String topic, @IntRange(from = 2) int participants,
                          @IntRange(from = 0) long firstMessageTime,
                          @IntRange(from = 0) long lastMessageTime, @NonNull String leaderId) {
        this.topic = topic;
        this.participants = participants;
        this.firstMessageTime = firstMessageTime;
        this.lastMessageTime = lastMessageTime;
        this.leaderId = leaderId;
    }

    /**
     * The Constructor that parses the parcel.
     *
     * @param in The parcel to parse.
     */
    protected ConferenceInfo(Parcel in) {
        topic = in.readString();
        participants = in.readInt();
        firstMessageTime = in.readLong();
        lastMessageTime = in.readLong();
        leaderId = in.readString();
    }

    /**
     * Returns the topic.
     *
     * @return The topic.
     **/
    @NonNull
    public String getTopic() {
        return topic;
    }

    /**
     * Returns the amount of participants in this conference.
     *
     * @return The amount.
     **/
    @IntRange(from = 2)
    public int getParticipants() {
        return participants;
    }

    /**
     * Returns the time of the first message.
     *
     * @return The time.
     **/
    @IntRange(from = 0)
    public long getFirstMessageTime() {
        return firstMessageTime;
    }

    /**
     * Returns the time of the last message.
     *
     * @return The time.
     **/
    @IntRange(from = 0)
    public long getLastMessageTime() {
        return lastMessageTime;
    }

    /**
     * Returns the id of the conferenceInfo leader.
     *
     * @return The id.
     **/
    @NonNull
    public String getLeaderId() {
        return leaderId;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceInfo that = (ConferenceInfo) o;

        if (participants != that.participants) return false;
        if (firstMessageTime != that.firstMessageTime) return false;
        if (lastMessageTime != that.lastMessageTime) return false;
        if (!topic.equals(that.topic)) return false;
        return leaderId.equals(that.leaderId);

    }

    @Override
    public int hashCode() {
        int result = topic.hashCode();
        result = 31 * result + participants;
        result = 31 * result + (int) (firstMessageTime ^ (firstMessageTime >>> 32));
        result = 31 * result + (int) (lastMessageTime ^ (lastMessageTime >>> 32));
        result = 31 * result + leaderId.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(topic);
        parcel.writeInt(participants);
        parcel.writeLong(firstMessageTime);
        parcel.writeLong(lastMessageTime);
        parcel.writeString(leaderId);
    }
}
