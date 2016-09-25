package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;

/**
 * Entity containing the relevant info of a subgroup.
 *
 * @author Ruben Gees
 */
public class Subgroup implements Parcelable, IdItem {

    public static final Parcelable.Creator<Subgroup> CREATOR = new Parcelable.Creator<Subgroup>() {
        @Override
        public Subgroup createFromParcel(Parcel source) {
            return new Subgroup(source);
        }

        @Override
        public Subgroup[] newArray(int size) {
            return new Subgroup[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "country")
    private String country;

    private Subgroup() {

    }

    /**
     * The constructor.
     *
     * @param id      The id.
     * @param name    The name.
     * @param country The country this subgroup is active in.
     */
    public Subgroup(@NonNull String id, @NonNull String name, @NonNull String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    protected Subgroup(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
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

        Subgroup subgroup = (Subgroup) o;

        if (!id.equals(subgroup.id)) return false;
        if (!name.equals(subgroup.name)) return false;
        return country.equals(subgroup.country);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
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
        dest.writeString(this.country);
    }
}
