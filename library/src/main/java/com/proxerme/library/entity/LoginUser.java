package com.proxerme.library.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class LoginUser implements Parcelable {
    public static final Creator<LoginUser> CREATOR = new Creator<LoginUser>() {
        public LoginUser createFromParcel(Parcel source) {
            return new LoginUser(source);
        }

        public LoginUser[] newArray(int size) {
            return new LoginUser[size];
        }
    };
    private String username;
    private String password;
    private String userId;
    private String imageLink;

    public LoginUser(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public LoginUser(@NonNull String username, @NonNull String password, @NonNull String userId,
                     @NonNull String image) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.imageLink = image;
    }

    protected LoginUser(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.userId = in.readString();
        this.imageLink = in.readString();
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @Nullable
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NonNull String userId) {
        this.userId = userId;
    }

    @Nullable
    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(@NonNull String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginUser loginUser = (LoginUser) o;

        if (!username.equals(loginUser.username)) return false;
        if (!password.equals(loginUser.password)) return false;
        if (userId != null ? !userId.equals(loginUser.userId) : loginUser.userId != null)
            return false;
        return !(imageLink != null ? !imageLink.equals(loginUser.imageLink) : loginUser.imageLink != null);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (imageLink != null ? imageLink.hashCode() : 0);
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
        dest.writeString(this.userId);
        dest.writeString(this.imageLink);
    }
}
