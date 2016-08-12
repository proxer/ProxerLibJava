package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.messenger.entity.conferenceInfo.User;

import java.util.Arrays;

/**
 * Class that represents the infos of a conference such as the participating users.
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

    @Body(name = "conference")
    Conference conference;
    @Body(name = "users")
    User[] user;

    /**
     * Private Constructor.
     */
    ConferenceInfo() {
    }

    /**
     * The Constructor.
     *
     * @param conference The conference object.
     * @param user       The user object.
     */
    public ConferenceInfo(Conference conference, User[] user) {
        this.conference = conference;
        this.user = user;
    }

    /**
     * The Constructor to parse a parcel.
     *
     * @param in The parcel to parse.
     */
    protected ConferenceInfo(Parcel in) {
        conference = in.readParcelable(Conference.class.getClassLoader());
        user = in.createTypedArray(User.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(conference, i);
        parcel.writeTypedArray(user, i);
    }

    /**
     * Returns the Conference.
     *
     * @return The Conference.‚
     **/
    public Conference getConference() {
        return conference;
    }

    /**
     * Returns the User.
     *
     * @return The User.
     **/
    public User[] getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceInfo that = (ConferenceInfo) o;

        if (!conference.equals(that.conference)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(user, that.user);

    }

    @Override
    public int hashCode() {
        int result = conference.hashCode();
        result = 31 * result + Arrays.hashCode(user);
        return result;
    }
}
