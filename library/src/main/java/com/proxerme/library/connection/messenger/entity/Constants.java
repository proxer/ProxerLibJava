package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;

import com.afollestad.bridge.annotations.Body;

/**
 * Class that represents the constants of the messaging module. Such as max text length,
 * request limits, e.g. how many conferences at once could be requested.
 *
 * @author Desnoo
 */
public class Constants implements Parcelable {
    public static final Creator<Constants> CREATOR = new Creator<Constants>() {
        @Override
        public Constants createFromParcel(Parcel in) {
            return new Constants(in);
        }

        @Override
        public Constants[] newArray(int size) {
            return new Constants[size];
        }
    };

    @Body(name = "textCount")
    int textCount;
    @Body(name = "conferenceLimit")
    int conferenceLimit;
    @Body(name = "messagesLimit")
    int messagesLimit;
    @Body(name = "userLimit")
    int userLimit;
    @Body(name = "topicCount")
    int topicCount;

    /**
     * Private constructor.
     */
    Constants() {
    }

    /**
     * The Constructor.
     *
     * @param textCount       The max length of a message.
     * @param conferenceLimit The max number of conferences to request with one request.
     * @param messagesLimit   The max number of messages of a conferenceInfo to request with one
     *                        request.
     * @param userLimit       The max count of users of an conferenceInfo.
     * @param topicCount      The max length of a conferenceInfo topic.
     */
    public Constants(@IntRange(from = 0) int textCount, @IntRange(from = 0) int conferenceLimit,
                     @IntRange(from = 0) int messagesLimit, @IntRange(from = 0) int userLimit,
                     @IntRange(from = 0) int topicCount) {
        this.textCount = textCount;
        this.conferenceLimit = conferenceLimit;
        this.messagesLimit = messagesLimit;
        this.userLimit = userLimit;
        this.topicCount = topicCount;
    }

    /**
     * The Constructor to parse from a parcel.
     *
     * @param in The parcel to parse.
     */
    protected Constants(Parcel in) {
        textCount = in.readInt();
        conferenceLimit = in.readInt();
        messagesLimit = in.readInt();
        userLimit = in.readInt();
        topicCount = in.readInt();
    }

    /**
     * Returns the max length of a message.
     *
     * @return The max length of a message.
     */
    @IntRange(from = 0)
    public int getTextCount() {
        return textCount;
    }

    /**
     * Returns the max number of conferences that can be requested by
     * {@link com.proxerme.library.connection.messenger.request.ConferencesRequest} with one request
     * to the API.
     *
     * @return The max number of conferences to request with one request.
     */
    @IntRange(from = 0)
    public int getConferenceLimit() {
        return conferenceLimit;
    }

    /**
     * Returns the max number of messages of an conferenceInfo that can be requested by
     * {@link com.proxerme.library.connection.messenger.request.MessagesRequest} one request to the
     * API.
     *
     * @return The max number of messages that can be requested for an conferenceInfo.
     */
    @IntRange(from = 0)
    public int getMessagesLimit() {
        return messagesLimit;
    }

    /**
     * Returns the max count of users that can be part of an conferenceInfo.
     *
     * @return The max count of users of a conferenceInfo.
     */
    @IntRange(from = 0)
    public int getUserLimit() {
        return userLimit;
    }

    /**
     * Returns the max length of the conferenceInfo topic.
     *
     * @return The max length of the conferenceInfo topic.
     */
    @IntRange(from = 0)
    public int getTopicCount() {
        return topicCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(textCount);
        parcel.writeInt(conferenceLimit);
        parcel.writeInt(messagesLimit);
        parcel.writeInt(userLimit);
        parcel.writeInt(topicCount);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constants constants = (Constants) o;
        if (textCount != constants.textCount) return false;
        if (conferenceLimit != constants.conferenceLimit) return false;
        if (messagesLimit != constants.messagesLimit) return false;
        if (userLimit != constants.userLimit) return false;
        return topicCount == constants.topicCount;
    }

    @Override
    public int hashCode() {
        int result = textCount;
        result = 31 * result + conferenceLimit;
        result = 31 * result + messagesLimit;
        result = 31 * result + userLimit;
        result = 31 * result + topicCount;
        return result;
    }
}
