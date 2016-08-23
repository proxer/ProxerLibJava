package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.FskParameter;
import com.proxerme.library.parameters.GenreParameter;
import com.proxerme.library.parameters.LicenseParameter;
import com.proxerme.library.parameters.MediumParameter;
import com.squareup.moshi.Json;

/**
 * Entity holding the detail data of an Entry (Anime, Manga)
 *
 * @author Desnoo
 */
public class EntryDetails implements Parcelable {

    public static final Creator<EntryDetails> CREATOR = new Creator<EntryDetails>() {
        @Override
        public EntryDetails createFromParcel(Parcel in) {
            return new EntryDetails(in);
        }

        @Override
        public EntryDetails[] newArray(int size) {
            return new EntryDetails[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "genre")
    private String genre;
    @Json(name = "fsk")
    private String fsk;
    @Json(name = "description")
    private String description;
    @Json(name = "medium")
    private String medium;
    @Json(name = "count")
    private int count;
    @Json(name = "state")
    private int state;
    @Json(name = "rate_sum")
    private int rateSum;
    @Json(name = "rate_count")
    private int rateCount;
    @Json(name = "clicks")
    private int clicks;
    @Json(name = "category")
    private String category;
    @Json(name = "license")
    private int license;

    /**
     * Private empty Constructor.
     */
    private EntryDetails() {
    }

    /**
     * Constructor of the entrycore data.
     *
     * @param id          The entry id.
     * @param name        The entry name.
     * @param genre       The genre.
     * @param fsk         The fsk ratings.
     * @param description The description.
     * @param medium      The medium.
     * @param count       The number of episodes.
     * @param state       The user view state.
     * @param rateSum     The sum of all ratings.
     * @param rateCount   The amount of ratings.
     * @param clicks      The amount of clicks.
     * @param category    The category name.
     * @param license     The license id.
     */
    public EntryDetails(@NonNull String id, @NonNull String name, @NonNull @GenreParameter.Genre String genre,
                        @NonNull String fsk, @NonNull String description,
                        @NonNull @MediumParameter.Medium String medium,
                        @IntRange(from = 1) int count, @IntRange(from = 0) int state,
                        @IntRange(from = 0) int rateSum, @IntRange(from = 0) int rateCount,
                        @IntRange(from = 0) int clicks, @NonNull @CategoryParameter.Category String category,
                        @IntRange(from = 0) @LicenseParameter.License int license) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.fsk = fsk;
        this.description = description;
        this.medium = medium;
        this.count = count;
        this.state = state;
        this.rateSum = rateSum;
        this.rateCount = rateCount;
        this.clicks = clicks;
        this.category = category;
        this.license = license;
    }

    /**
     * Parser to retain the instance back from the parcel.
     *
     * @param in the parcel to parse.
     */
    protected EntryDetails(Parcel in) {
        id = in.readString();
        name = in.readString();
        genre = in.readString();
        fsk = in.readString();
        description = in.readString();
        medium = in.readString();
        count = in.readInt();
        state = in.readInt();
        rateSum = in.readInt();
        rateCount = in.readInt();
        clicks = in.readInt();
        category = in.readString();
    }

    /**
     * Returns the id of this entry.
     *
     * @return The id.
     */
    @NonNull
    public String getId() {
        return id;
    }

    /**
     * Returns the name of this entry.
     *
     * @return the name.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the genre of this entry.
     *
     * @return The genre.
     */
    @NonNull
    @GenreParameter.Genre
    public String getGenre() {
        return genre;
    }

    /**
     * Returns an array of fsk names of this entry.
     *
     * @return An array of fsk names.
     */
    @SuppressWarnings("WrongConstant")
    @NonNull
    @FskParameter.FskConstraint
    public String[] getFsk() {
        return fsk.split(" ");
    }

    /**
     * Returns the description of this entry.
     *
     * @return The description.
     */
    @NonNull
    public String getDescription() {
        return description;
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

    /**
     * Get the overall amount of episodes/chapters of this entry. It does include not available episodes.
     *
     * @return The amount of episodes/chapters.
     */
    @IntRange(from = 0)
    public int getCount() {
        return count;
    }

    /**
     * Returns the view state for the current user of this entry. If no user is set then this is 0.
     *
     * @return The view state for the current user.
     */
    @IntRange(from = 0)
    public int getState() {
        return state;
    }

    /**
     * Returns the sum of all ratings of this entry.
     *
     * @return The sum of ratings.
     */
    @IntRange(from = 0)
    public int getRateSum() {
        return rateSum;
    }

    /**
     * Returns the amount of ratings of this entry.
     *
     * @return The amount of ratings.
     */
    @IntRange(from = 0)
    public int getRateCount() {
        return rateCount;
    }

    /**
     * Returns the sum of all ratings of this entry.
     *
     * @return The sum of all ratings.
     */
    @FloatRange(from = -1.0f)
    public float getRating() {
        if (rateCount <= 0) {
            return -1;
        } else {
            return rateSum / rateCount;
        }
    }

    /**
     * Returns the number of views of this entry. Will be deleted every 3 months.
     *
     * @return The number of views.
     */
    @IntRange(from = 0)
    public int getClicks() {
        return clicks;
    }

    /**
     * Returns the name of the category of this entry.
     *
     * @return The name of the category.
     */
    @CategoryParameter.Category
    public String getCategory() {
        return category;
    }

    /**
     * Returns the license state of this entry.
     *
     * @return The license state.
     */
    @LicenseParameter.License
    public int getLicense() {
        return license;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(genre);
        dest.writeString(fsk);
        dest.writeString(description);
        dest.writeString(medium);
        dest.writeInt(count);
        dest.writeInt(state);
        dest.writeInt(rateSum);
        dest.writeInt(rateCount);
        dest.writeInt(clicks);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryDetails entryDetails = (EntryDetails) o;

        if (!id.equals(entryDetails.id)) return false;
        if (count != entryDetails.count) return false;
        if (state != entryDetails.state) return false;
        if (rateSum != entryDetails.rateSum) return false;
        if (rateCount != entryDetails.rateCount) return false;
        if (clicks != entryDetails.clicks) return false;
        if (!name.equals(entryDetails.name)) return false;
        if (!genre.equals(entryDetails.genre)) return false;
        if (!fsk.equals(entryDetails.fsk)) return false;
        if (!description.equals(entryDetails.description)) return false;
        if (!medium.equals(entryDetails.medium)) return false;
        if (!category.equals(entryDetails.category)) return false;
        return license == entryDetails.license;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + fsk.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + count;
        result = 31 * result + state;
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
        result = 31 * result + clicks;
        result = 31 * result + category.hashCode();
        result = 31 * result + license;
        return result;
    }
}
