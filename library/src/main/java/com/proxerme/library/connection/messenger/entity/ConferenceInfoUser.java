package com.proxerme.library.connection.messenger.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.squareup.moshi.Json;

/**
 * Entity that represents a participant in a {@link Conference}.
 *
 * @author Desnoo
 */
public class ConferenceInfoUser implements Parcelable, IdItem, ImageItem {

    public static final Creator<ConferenceInfoUser> CREATOR = new Creator<ConferenceInfoUser>() {
        @Override
        public ConferenceInfoUser createFromParcel(Parcel in) {
            return new ConferenceInfoUser(in);
        }

        @Override
        public ConferenceInfoUser[] newArray(int size) {
            return new ConferenceInfoUser[size];
        }
    };

    @Json(name = "uid")
    private String id;
    @Json(name = "avatar")
    private String imageId;
    @Json(name = "username")
    private String username;
    @Json(name = "status")
    private String status;

    private ConferenceInfoUser() {
    }

    /**
     * The Constructor.
     *
     * @param id       The user id.
     * @param avatar   The avatar id.
     * @param username The user's name.
     * @param status   The current status message.
     */
    public ConferenceInfoUser(@NonNull String id, @NonNull String avatar, @NonNull String username,
                              @NonNull String status) {
        this.id = id;
        this.imageId = avatar;
        this.username = username;
        this.status = status;
    }

    protected ConferenceInfoUser(Parcel in) {
        id = in.readString();
        imageId = in.readString();
        username = in.readString();
        status = in.readString();
    }

    /**
     * Returns the username.
     *
     * @return The username.
     **/
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * Returns the status.
     *
     * @return The status.
     **/
    @NonNull
    public String getStatus() {
        return status;
    }

    /**
     * Returns the id of the user.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Returns the id of the users image.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(imageId);
        parcel.writeString(username);
        parcel.writeString(status);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferenceInfoUser conferenceInfoUser = (ConferenceInfoUser) o;

        if (!id.equals(conferenceInfoUser.id)) return false;
        if (!imageId.equals(conferenceInfoUser.imageId)) return false;
        if (!username.equals(conferenceInfoUser.username)) return false;
        return status.equals(conferenceInfoUser.status);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }


}