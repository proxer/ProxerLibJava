package com.proxerme.library.connection.user.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.squareup.moshi.Json;

/**
 * Entity holding information for the login and about the user after the login.
 *
 * @author Ruben Gees
 */
public class User implements Parcelable, IdItem, ImageItem {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Json(name = "uid")
    private String id;
    @Json(name = "avatar")
    private String imageId;
    @Json(name = "token")
    private String loginToken;

    private User() {

    }

    /**
     * The Constructor.
     *
     * @param id         The id of the user.
     * @param image      The profile picture of the user.
     * @param loginToken The login token, usable for further authentication.
     */
    public User(@NonNull String id, @NonNull String image, @NonNull String loginToken) {
        this.id = id;
        this.imageId = image;
        this.loginToken = loginToken;
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.imageId = in.readString();
        this.loginToken = in.readString();
    }

    /**
     * Returns the id of the user.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the profile picture of the user.
     *
     * @return The image.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    /**
     * Returns the login token of the user for further authentification.
     *
     * @return The token.
     */
    @NonNull
    public String getLoginToken() {
        return loginToken;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!imageId.equals(user.imageId)) return false;
        return loginToken.equals(user.loginToken);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + loginToken.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.imageId);
        dest.writeString(this.loginToken);
    }
}
