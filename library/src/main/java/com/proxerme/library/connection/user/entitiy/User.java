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
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private String username;
    private String password;

    @Json(name = "uid")
    private String id;
    @Json(name = "avatar")
    private String imageId;

    private User() {

    }

    /**
     * Constructor for a user before a login.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor for a user after the login.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param id       The id of the user.
     * @param image    The profile picture of the user.
     */
    public User(@NonNull String username, @NonNull String password, @NonNull String id,
                @NonNull String image) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.imageId = image;
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.id = in.readString();
        this.imageId = in.readString();
    }

    /**
     * Returns the username.
     *
     * @return The username.
     */
    @NonNull
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username The new username.
     */
    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    /**
     * Return the password of the user.
     *
     * @return The password.
     */
    @NonNull
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password The new password.
     */
    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    /**
     * Returns the id of the user.
     *
     * @return The id.
     * @throws RuntimeException If the user has no id yet, meaning he is not logged in yet.
     */
    @NonNull
    @Override
    public String getId() throws UserInitializationException {
        if (id == null) {
            throw new UserInitializationException("User has no id yet.");
        }

        return id;
    }

    /**
     * Returns the profile picture of the user.
     *
     * @return The image.
     * @throws RuntimeException If the user has not image yet, meaning he is not logged in yet.
     */
    @NonNull
    @Override
    public String getImageId() throws UserInitializationException {
        if (imageId == null) {
            throw new UserInitializationException("User has no image yet.");
        }

        return imageId;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (id != null ? !id.equals(user.id) : user.id != null)
            return false;
        return !(imageId != null ? !imageId.equals(user.imageId) : user.imageId != null);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (imageId != null ? imageId.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.id);
        dest.writeString(this.imageId);
    }

    public static class UserInitializationException extends RuntimeException {

        public UserInitializationException(String message) {
            super(message);
        }
    }
}