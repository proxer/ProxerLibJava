package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.ImageItem;
import com.proxerme.library.parameters.CountryParameter.Country;
import com.squareup.moshi.Json;

/**
 * Entity holding the full info of a translator group
 *
 * @author Ruben Gees
 */
public class TranslatorGroup implements IdItem, ImageItem, Parcelable {

    public static final Parcelable.Creator<TranslatorGroup> CREATOR = new Parcelable.Creator<TranslatorGroup>() {
        @Override
        public TranslatorGroup createFromParcel(Parcel source) {
            return new TranslatorGroup(source);
        }

        @Override
        public TranslatorGroup[] newArray(int size) {
            return new TranslatorGroup[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "link")
    private String link;
    @Json(name = "country")
    private String country;
    @Json(name = "image")
    private String imageId;
    @Json(name = "description")
    private String description;
    @Json(name = "count")
    private int count;
    @Json(name = "cprojects")
    private int projectAmount;

    /**
     * The constructor.
     *
     * @param id            The id.
     * @param name          The name.
     * @param link          The link to the homepage.
     * @param country       The country.
     * @param imageId       The image. Can be empty. This is directly usable.
     * @param description   The description. Can be empty.
     * @param count         The clicks.
     * @param projectAmount The amount of projects
     */
    public TranslatorGroup(@NonNull String id, @NonNull String name, @NonNull String link,
                           @NonNull @Country String country, @NonNull String imageId,
                           @NonNull String description, @IntRange(from = 0) int count,
                           @IntRange(from = 0) int projectAmount) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.country = country;
        this.imageId = imageId;
        this.description = description;
        this.count = count;
        this.projectAmount = projectAmount;
    }

    protected TranslatorGroup(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.link = in.readString();
        this.country = in.readString();
        this.imageId = in.readString();
        this.description = in.readString();
        this.count = in.readInt();
        this.projectAmount = in.readInt();
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @NonNull
    @Override
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
     * Returns the link to the homepage.
     *
     * @return The link.
     */
    @NonNull
    public String getLink() {
        return link;
    }

    /**
     * Returns the country this group is active in.
     *
     * @return The country.
     */
    @NonNull
    @Country
    public String getCountry() {
        return country;
    }

    /**
     * Returns the image link.
     *
     * @return The image link.
     */
    @NonNull
    @Override
    public String getImageId() {
        return imageId;
    }

    /**
     * Returns the description.
     *
     * @return The description.
     */
    @NonNull
    public String getDescription() {
        return description;
    }

    /**
     * Returns the count.
     *
     * @return The count.
     */
    @IntRange(from = 0)
    public int getCount() {
        return count;
    }

    /**
     * Returns the amount of projects.
     *
     * @return The amount of projects.
     */
    @IntRange(from = 0)
    public int getProjectAmount() {
        return projectAmount;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslatorGroup that = (TranslatorGroup) o;

        if (count != that.count) return false;
        if (projectAmount != that.projectAmount) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!link.equals(that.link)) return false;
        if (!country.equals(that.country)) return false;
        if (!imageId.equals(that.imageId)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + link.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + count;
        result = 31 * result + projectAmount;
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
        dest.writeString(this.link);
        dest.writeString(this.country);
        dest.writeString(this.imageId);
        dest.writeString(this.description);
        dest.writeInt(this.count);
        dest.writeInt(this.projectAmount);
    }
}
