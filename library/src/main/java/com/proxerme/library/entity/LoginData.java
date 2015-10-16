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
public class LoginData implements Parcelable, IdItem, ImageItem {

    public static final Creator<LoginData> CREATOR = new Creator<LoginData>() {
        public LoginData createFromParcel(Parcel source) {
            return new LoginData(source);
        }

        public LoginData[] newArray(int size) {
            return new LoginData[size];
        }
    };
    private String id;
    private String imageLink;

    public LoginData(String id, String image) {
        this.id = id;
        this.imageLink = image;
    }

    protected LoginData(Parcel in) {
        this.id = in.readString();
        this.imageLink = in.readString();
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    @Override
    public String getImageId() {
        return imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginData loginData = (LoginData) o;

        if (id != null ? !id.equals(loginData.id) : loginData.id != null) return false;
        return !(imageLink != null ? !imageLink.equals(loginData.imageLink) : loginData.imageLink != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imageLink != null ? imageLink.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.imageLink);
    }
}
