package com.proxerme.library.connection.ucp.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.MediumParameter;
import com.squareup.moshi.Json;

/**
 * Entity representing a single entry in the UCP topten list.
 *
 * @author Ruben Gees
 */
public class UcpToptenEntry implements Parcelable {

    public static final Parcelable.Creator<UcpToptenEntry> CREATOR = new Parcelable.Creator<UcpToptenEntry>() {
        @Override
        public UcpToptenEntry createFromParcel(Parcel source) {
            return new UcpToptenEntry(source);
        }

        @Override
        public UcpToptenEntry[] newArray(int size) {
            return new UcpToptenEntry[size];
        }
    };

    @Json(name = "fid")
    private String id;
    @Json(name = "eid")
    private String entryId;
    @Json(name = "name")
    private String name;
    @Json(name = "medium")
    private String medium;
    @Json(name = "kat")
    private String category;

    /**
     * The constructor.
     *
     * @param id       The id.
     * @param entryId  The id of the entry. This can be used to fetch info.
     * @param name     The name.
     * @param medium   The medium (Animeseries, ...)
     * @param category The category.
     */
    public UcpToptenEntry(@NonNull String id, @NonNull String entryId, @NonNull String name,
                          @MediumParameter.Medium @NonNull String medium,
                          @CategoryParameter.Category @NonNull String category) {
        this.id = id;
        this.entryId = entryId;
        this.name = name;
        this.medium = medium;
        this.category = category;
    }

    protected UcpToptenEntry(Parcel in) {
        this.id = in.readString();
        this.entryId = in.readString();
        this.name = in.readString();
        this.medium = in.readString();
        this.category = in.readString();
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the associated entry.
     *
     * @return The id.
     */
    @NonNull
    public String getEntryId() {
        return entryId;
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
     * Returns the medium.
     *
     * @return The medium.
     */
    @NonNull
    @MediumParameter.Medium
    public String getMedium() {
        return medium;
    }

    /**
     * Returns the category.
     *
     * @return The category.
     */
    @NonNull
    @CategoryParameter.Category
    public String getCategory() {
        return category;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UcpToptenEntry that = (UcpToptenEntry) o;

        if (!id.equals(that.id)) return false;
        if (!entryId.equals(that.entryId)) return false;
        if (!name.equals(that.name)) return false;
        if (!medium.equals(that.medium)) return false;
        return category.equals(that.category);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.entryId);
        dest.writeString(this.name);
        dest.writeString(this.medium);
        dest.writeString(this.category);
    }
}
