package com.proxerme.library.connection.user.entitiy;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.MediumParameter;
import com.squareup.moshi.Json;

/**
 * Entity representing a single Topten entry.
 *
 * @author Ruben Gees
 */
public class ToptenEntry implements Parcelable, IdItem {

    public static final Parcelable.Creator<ToptenEntry> CREATOR = new Parcelable.Creator<ToptenEntry>() {
        @Override
        public ToptenEntry createFromParcel(Parcel source) {
            return new ToptenEntry(source);
        }

        @Override
        public ToptenEntry[] newArray(int size) {
            return new ToptenEntry[size];
        }
    };

    @Json(name = "eid")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "kat")
    private String category;
    @Json(name = "medium")
    private String medium;

    private ToptenEntry() {

    }

    /**
     * The constructor.
     *
     * @param id       The id of the entry.
     * @param name     The name of the entry.
     * @param category The category.
     * @param medium   The medium.
     */
    public ToptenEntry(@NonNull String id, @NonNull String name, @NonNull String category,
                       @NonNull String medium) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.medium = medium;
    }

    protected ToptenEntry(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.category = in.readString();
        this.medium = in.readString();
    }

    /**
     * Returns the id of this entry.
     *
     * @return The id.
     */
    @Override
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the name of this entry.
     *
     * @return The name.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the category of this entry.
     *
     * @return The category.
     */
    @NonNull
    @CategoryParameter.Category
    public String getCategory() {
        return category;
    }

    /**
     * Returns the medium of this entry.
     *
     * @return The medium.
     */
    @NonNull
    @MediumParameter.Medium
    public String getMedium() {
        return medium;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToptenEntry that = (ToptenEntry) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!category.equals(that.category)) return false;
        return medium.equals(that.medium);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + medium.hashCode();
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
        dest.writeString(this.category);
        dest.writeString(this.medium);
    }
}
