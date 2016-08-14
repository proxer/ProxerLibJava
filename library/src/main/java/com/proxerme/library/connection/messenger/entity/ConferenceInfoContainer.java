package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.conferenceInfo.ConferenceInfoUser;

import java.util.Arrays;

/**
 * Class that represents the infos of a conference such as the participating users.
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

    @Body(name = "conference")
    Conference conference;
    @Body(name = "users")
    ConferenceInfoUser[] conferenceInfoUser;

    /**
     * Private Constructor.
     */
    ConferenceInfoContainer() {
    }

    /**
     * The Constructor.
     *
     * @param conference The conference object.
     * @param conferenceInfoUser       The user object.
     */
    public ConferenceInfoContainer(@NonNull Conference conference, @NonNull ConferenceInfoUser[] conferenceInfoUser) {
        this.conference = conference;
        this.conferenceInfoUser = conferenceInfoUser;
    }

    /**
     * The Constructor to parse a parcel.
     *
     * @param in The parcel to parse.
     */
    protected ConferenceInfoContainer(Parcel in) {
        conference = in.readParcelable(Conference.class.getClassLoader());
        conferenceInfoUser = in.createTypedArray(ConferenceInfoUser.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(conference, i);
        parcel.writeTypedArray(conferenceInfoUser, i);
    }

    /**
     * Returns the ConferenceInfo.
     *
     * @return The ConferenceInfo.‚
     **/
    @NonNull
    public Conference getConference() {
        return conference;
    }

    /**
     * Returns the User.
     *
     * @return The User.
     **/
    @NonNull
    public ConferenceInfoUser[] getConferenceInfoUser() {
        return conferenceInfoUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceInfoContainer that = (ConferenceInfoContainer) o;

        if (!conference.equals(that.conference)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(conferenceInfoUser, that.conferenceInfoUser);

    }

    @Override
    public int hashCode() {
        int result = conference.hashCode();
        result = 31 * result + Arrays.hashCode(conferenceInfoUser);
        return result;
    }
}
