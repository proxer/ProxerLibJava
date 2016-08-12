package com.proxerme.library.connection.messenger.entity.conferenceInfo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;

/**
 * The class that represents a user.
 *
 * @author Desnoo
 */
public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Body(name = "uid")
    String uid;
    @Body(name = "avatar")
    String avatarId;
    @Body(name = "username")
    String username;
    @Body(name = "status")
    String status;

    /**
     * Private Constructor.
     */
    User() {
    }

    /**
     * The Constructor.
     *
     * @param uid      The user id.
     * @param avatar   The avatar id.
     * @param username The user's name.
     * @param status   The current status message.
     */
    public User(@NonNull String uid, @NonNull String avatar, @NonNull String username, @NonNull String status) {
        this.uid = uid;
        this.avatarId = avatar;
        this.username = username;
        this.status = status;
    }

    /**
     * The Constructor to parse the parcel.
     *
     * @param in The parcel to parse.
     */
    protected User(Parcel in) {
        uid = in.readString();
        avatarId = in.readString();
        username = in.readString();
        status = in.readString();
    }

    /**
     * Returns the Uid.
     *
     * @return The Uid.
     **/
    @NonNull
    public String getUid() {
        return uid;
    }

    /**
     * Returns the Avatar.
     *
     * @return The Avatar.
     **/
    @NonNull
    public String getAvatar() {
        return avatarId;
    }

    /**
     * Returns the Username.
     *
     * @return The Username.
     **/
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * Returns the Status.
     *
     * @return The Status.
     **/
    @NonNull
    public String getStatus() {
        return status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uid);
        parcel.writeString(avatarId);
        parcel.writeString(username);
        parcel.writeString(status);
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!uid.equals(user.uid)) return false;
        if (!avatarId.equals(user.avatarId)) return false;
        if (!username.equals(user.username)) return false;
        return status.equals(user.status);

    }

    @Override
    public int hashCode() {
        int result = uid.hashCode();
        result = 31 * result + avatarId.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
