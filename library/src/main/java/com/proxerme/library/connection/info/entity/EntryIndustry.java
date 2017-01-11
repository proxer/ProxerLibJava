package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CountryParameter.Country;
import com.proxerme.library.parameters.IndustryTypeParameter.IndustryType;
import com.squareup.moshi.Json;

/**
 * Entity containing the relevant info of an industry, associated with an {@link Entry}.
 *
 * @author Ruben Gees
 */
public class EntryIndustry implements IdItem, Parcelable {

    public static final Parcelable.Creator<EntryIndustry> CREATOR = new Parcelable.Creator<EntryIndustry>() {
        @Override
        public EntryIndustry createFromParcel(Parcel source) {
            return new EntryIndustry(source);
        }

        @Override
        public EntryIndustry[] newArray(int size) {
            return new EntryIndustry[size];
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

    private EntryIndustry() {
    }

    /**
     * The constructor.
     *
     * @param id      The id.
     * @param name    The name.
     * @param type    The type of the industry.
     * @param country The country the industry resides in.
     */
    public EntryIndustry(@NonNull String id, @NonNull String name,
                         @NonNull @IndustryType String type, @NonNull @Country String country) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.country = country;
    }

    protected EntryIndustry(Parcel in) {
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
    @IndustryType
    public String getType() {
        return type;
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

        EntryIndustry industry = (EntryIndustry) o;

        if (!id.equals(industry.id)) return false;
        if (!name.equals(industry.name)) return false;
        if (!type.equals(industry.type)) return false;
        return country.equals(industry.country);
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
