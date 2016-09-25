package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;

/**
 * An entity which holds the info of a single publisher.
 *
 * @author Ruben Gees
 */
public class Publisher implements IdItem, Parcelable {

    public static final Parcelable.Creator<Publisher> CREATOR = new Parcelable.Creator<Publisher>() {
        @Override
        public Publisher createFromParcel(Parcel source) {
            return new Publisher(source);
        }

        @Override
        public Publisher[] newArray(int size) {
            return new Publisher[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "type")
    private String type;
    @Json(name = "country")
    private String country;

    private Publisher() {
    }

    /**
     * The constructor.
     *
     * @param id      The id.
     * @param name    The name.
     * @param type    The type of the publisher.
     * @param country The country the publisher resides in.
     */
    public Publisher(@NonNull String id, @NonNull String name, @NonNull String type,
                     @NonNull String country) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.country = country;
    }

    protected Publisher(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.country = in.readString();
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @Override
    @NonNull
    public String getId() {
        return id;
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

    /**
     * Returns the type.
     *
     * @return The type.
     */
    @NonNull
    public String getType() {
        return type;
    }

    /**
     * Returns the country.
     *
     * @return The country.
     */
    @NonNull
    public String getCountry() {
        return country;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        if (!id.equals(publisher.id)) return false;
        if (!name.equals(publisher.name)) return false;
        if (!type.equals(publisher.type)) return false;
        return country.equals(publisher.country);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.country);
    }
}
