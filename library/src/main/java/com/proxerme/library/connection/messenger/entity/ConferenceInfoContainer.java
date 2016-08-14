package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;

import java.util.Arrays;

/**
 * Class that represents the info of a conference such as the participating users.
 *
 * @author Desnoo
 */
public class ConferenceInfoContainer implements Parcelable {

    public static final Creator<ConferenceInfoContainer> CREATOR = new Creator<ConferenceInfoContainer>() {
        @Override
        public ConferenceInfoContainer createFromParcel(Parcel in) {
            return new ConferenceInfoContainer(in);
        }

        @Override
        public ConferenceInfoContainer[] newArray(int size) {
            return new ConferenceInfoContainer[size];
        }
    };

    @Body(name = "conferenceInfo")
    ConferenceInfo conferenceInfo;
    @Body(name = "users")
    ConferenceInfoUser[] participants;

    /**
     * Private Constructor.
     */
    ConferenceInfoContainer() {
    }

    /**
     * The Constructor.
     *
     * @param conferenceInfo The conferenceInfo object.
     * @param participants   The participants of the conference.
     */
    public ConferenceInfoContainer(@NonNull ConferenceInfo conferenceInfo,
                                   @NonNull ConferenceInfoUser[] participants) {
        this.conferenceInfo = conferenceInfo;
        this.participants = participants;
    }

    /**
     * The Constructor to parse a parcel.
     *
     * @param in The parcel to parse.
     */
    protected ConferenceInfoContainer(Parcel in) {
        conferenceInfo = in.readParcelable(Conference.class.getClassLoader());
        participants = in.createTypedArray(ConferenceInfoUser.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(conferenceInfo, i);
        parcel.writeTypedArray(participants, i);
    }

    /**
     * Returns the ConferenceInfo.
     *
     * @return The ConferenceInfo.
     **/
    @NonNull
    public ConferenceInfo getConferenceInfo() {
        return conferenceInfo;
    }

    /**
     * Returns the participants.
     *
     * @return The participants.
     **/
    @NonNull
    public ConferenceInfoUser[] getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceInfoContainer that = (ConferenceInfoContainer) o;

        if (!conferenceInfo.equals(that.conferenceInfo)) return false;
        return Arrays.equals(participants, that.participants);

    }

    @Override
    public int hashCode() {
        int result = conferenceInfo.hashCode();
        result = 31 * result + Arrays.hashCode(participants);
        return result;
    }
}
