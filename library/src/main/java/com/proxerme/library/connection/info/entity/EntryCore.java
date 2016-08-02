package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.FskParameter;
import com.proxerme.library.parameters.GenreParameter;
import com.proxerme.library.parameters.LicenseParameter;
import com.proxerme.library.parameters.MediumParameter;

/**
 * Represents the core data of an Entry (Anime, Manga)
 *
 * @author Desnoo
 */
public class EntryCore implements Parcelable {

    public static final Creator<EntryCore> CREATOR = new Creator<EntryCore>() {
        @Override
        public EntryCore createFromParcel(Parcel in) {
            return new EntryCore(in);
        }

        @Override
        public EntryCore[] newArray(int size) {
            return new EntryCore[size];
        }
    };

    @Body(name = "id")
    String id;
    @Body(name = "name")
    String name;
    @Body(name = "genre")
    String genre;
    @Body(name = "fsk")
    String fsk;
    @Body(name = "description")
    String description;
    @Body(name = "medium")
    String medium;
    @Body(name = "count")
    int count;
    @Body(name = "state")
    int state;
    @Body(name = "rate_sum")
    int rateSum;
    @Body(name = "rate_count")
    int rateCount;
    @Body(name = "clicks")
    int clicks;
    @Body(name = "category")
    String category;
    @Body(name = "license")
    int license;

    /**
     * Private empty Constructor.
     */
    EntryCore() {
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
    public EntryCore(@NonNull String id, @NonNull String name, @NonNull @GenreParameter.Genre String genre,
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
    protected EntryCore(Parcel in) {
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
    public int getClicks() {
        return clicks;
    }

    /**
     * Returns the name of the category of this entry.
     *
     * @return The name of the category.
     */
    @NonNull
    @CategoryParameter.Category
    public String getCategory() {
        return category;
    }

    /**
     * Returns the license state of this entry.
     *
     * @return The license state.
     */
    @IntRange(from = 0)
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

        EntryCore entryCore = (EntryCore) o;

        if (!id.equals(entryCore.id)) return false;
        if (count != entryCore.count) return false;
        if (state != entryCore.state) return false;
        if (rateSum != entryCore.rateSum) return false;
        if (rateCount != entryCore.rateCount) return false;
        if (clicks != entryCore.clicks) return false;
        if (!name.equals(entryCore.name)) return false;
        if (!genre.equals(entryCore.genre)) return false;
        if (!fsk.equals(entryCore.fsk)) return false;
        if (!description.equals(entryCore.description)) return false;
        if (!medium.equals(entryCore.medium)) return false;
        if (!category.equals(entryCore.category)) return false;
        return license == entryCore.license;

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
