package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.ImageItem;

/**
 * Entity with info about a single anime hoster.
 *
 * @author Ruben Gees
 */
public class Hoster implements ImageItem, Parcelable {

    public static final Parcelable.Creator<Hoster> CREATOR = new Parcelable.Creator<Hoster>() {
        @Override
        public Hoster createFromParcel(Parcel source) {
            return new Hoster(source);
        }

        @Override
        public Hoster[] newArray(int size) {
            return new Hoster[size];
        }
    };

    private String name;
    private String imageId;

    /**
     * The constructor.
     *
     * @param name    The name,
     * @param imageId The image id.
     */
    public Hoster(@NonNull String name, @NonNull String imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    protected Hoster(Parcel in) {
        this.name = in.readString();
        this.imageId = in.readString();
    }

    /**
     * Returns the image id.
     *
     * @return The image id.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    /**
     * Returns the name.
     *
     * @return The name.
     */
    @NonNull
    public String getName() {
        return name;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hoster that = (Hoster) o;

        if (!name.equals(that.name)) return false;
        return imageId.equals(that.imageId);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + imageId.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.imageId);
    }
}
