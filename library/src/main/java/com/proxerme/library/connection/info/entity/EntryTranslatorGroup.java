package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CountryParameter.Country;
import com.squareup.moshi.Json;

/**
 * Entity containing the relevant info of a translator group, associated with an {@link Entry}.
 *
 * @author Ruben Gees
 */
public class EntryTranslatorGroup implements Parcelable, IdItem {

    public static final Parcelable.Creator<EntryTranslatorGroup> CREATOR = new Parcelable.Creator<EntryTranslatorGroup>() {
        @Override
        public EntryTranslatorGroup createFromParcel(Parcel source) {
            return new EntryTranslatorGroup(source);
        }

        @Override
        public EntryTranslatorGroup[] newArray(int size) {
            return new EntryTranslatorGroup[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "country")
    private String country;

    private EntryTranslatorGroup() {

    }

    /**
     * The constructor.
     *
     * @param id      The id.
     * @param name    The name.
     * @param country The country this translator group is active in.
     */
    public EntryTranslatorGroup(@NonNull String id, @NonNull String name, @NonNull @Country String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    protected EntryTranslatorGroup(Parcel in) {
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
    @Country
    public String getCountry() {
        return country;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryTranslatorGroup translatorGroup = (EntryTranslatorGroup) o;

        if (!id.equals(translatorGroup.id)) return false;
        if (!name.equals(translatorGroup.name)) return false;
        return country.equals(translatorGroup.country);
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
