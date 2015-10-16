package com.proxerme.library.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;

/**
 * Todo: Describe Class
 *
 * @author Ruben Gees
 */
public class LoginUser implements Parcelable, IdItem, ImageItem {
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
    private String id;
    private String imageId;

    public LoginUser(@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public LoginUser(@NonNull String username, @NonNull String password, @NonNull String id,
                     @NonNull String image) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.imageId = image;
    }

    protected LoginUser(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
        this.id = in.readString();
        this.imageId = in.readString();
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    @Override
    public String getId() throws RuntimeException {
        if (id == null) {
            throw new RuntimeException("User has no id yet.");
        }

        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String getImageId() throws RuntimeException {
        if (imageId == null) {
            throw new RuntimeException("User has no image yet.");
        }

        return imageId;
    }

    public void setImageId(@NonNull String imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginUser loginUser = (LoginUser) o;

        if (!username.equals(loginUser.username)) return false;
        if (!password.equals(loginUser.password)) return false;
        if (id != null ? !id.equals(loginUser.id) : loginUser.id != null)
            return false;
        return !(imageId != null ? !imageId.equals(loginUser.imageId) : loginUser.imageId != null);

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
}
