package com.proxerme.library.connection.messenger.entity.conferenceInfo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;

/**
 * The class that represents the ConferenceInfo Conference entity.
 * This class is different to the {@link com.proxerme.library.connection.messenger.entity.Conference}
 *
 * @author Desnoo
 */
public class Conference implements Parcelable {

    public static final Creator<Conference> CREATOR = new Creator<Conference>() {
        @Override
        public Conference createFromParcel(Parcel in) {
            return new Conference(in);
        }

        @Override
        public Conference[] newArray(int size) {
            return new Conference[size];
        }
    };

    @Body(name = "topic")
    String topic;
    @Body(name = "count")
    int participants;
    @Body(name = "timestampStart")
    long timestampStart;
    @Body(name = "timestampEnd")
    long timestampEnd;
    @Body(name = "leader")
    String leaderId;

    /**
     * Private Constructor.
     */
    Conference() {
    }

    /**
     * The Constructor.
     *
     * @param topic          The topic.
     * @param participants   The participants of this conference.
     * @param timestampStart The timestamp when the conference started.
     * @param timestampEnd   The timestamp when the last message of the conference was sent.
     * @param leaderId       The user id of the conference leader.
     */
    public Conference(@NonNull String topic, @IntRange(from = 1) int participants,
                      @IntRange(from = 0) long timestampStart, @IntRange(from = 0) long timestampEnd,
                      @NonNull String leaderId) {
        this.topic = topic;
        this.participants = participants;
        this.timestampStart = timestampStart;
        this.timestampEnd = timestampEnd;
        this.leaderId = leaderId;
    }

    /**
     * The Constructor parses the parcel.
     *
     * @param in The parcel to parse.
     */
    protected Conference(Parcel in) {
        topic = in.readString();
        participants = in.readInt();
        timestampStart = in.readLong();
        timestampEnd = in.readLong();
        leaderId = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(topic);
        parcel.writeInt(participants);
        parcel.writeLong(timestampStart);
        parcel.writeLong(timestampEnd);
        parcel.writeString(leaderId);
    }


    /**
     * Returns the Topic.
     *
     * @return The Topic.
     **/
    @NonNull
    public String getTopic() {
        return topic;
    }

    /**
     * Returns the Count.
     *
     * @return The Count.
     **/
    @IntRange(from = 1)
    public int getParticipants() {
        return participants;
    }

    /**
     * Returns the TimestampStart.
     *
     * @return The TimestampStart.
     **/
    @IntRange(from = 0)
    public long getTimestampStart() {
        return timestampStart;
    }

    /**
     * Returns the TimestampEnd.
     *
     * @return The TimestampEnd.
     **/
    @IntRange(from = 0)
    public long getTimestampEnd() {
        return timestampEnd;
    }

    /**
     * Returns the Leader.
     *
     * @return The Leader.
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

        Conference that = (Conference) o;

        if (participants != that.participants) return false;
        if (timestampStart != that.timestampStart) return false;
        if (timestampEnd != that.timestampEnd) return false;
        if (!topic.equals(that.topic)) return false;
        return leaderId.equals(that.leaderId);

    }

    @Override
    public int hashCode() {
        int result = topic.hashCode();
        result = 31 * result + participants;
        result = 31 * result + (int) (timestampStart ^ (timestampStart >>> 32));
        result = 31 * result + (int) (timestampEnd ^ (timestampEnd >>> 32));
        result = 31 * result + leaderId.hashCode();
        return result;
    }
}
