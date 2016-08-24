package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.parameters.FskParameter;
import com.proxerme.library.parameters.GenreParameter.Genre;
import com.proxerme.library.parameters.LicenseParameter.License;
import com.proxerme.library.parameters.MediumParameter.Medium;
import com.squareup.moshi.Json;

/**
 * Entity holding the detail data of an Entry (Anime, Manga)
 *
 * @author Desnoo
 */
public class EntryCore implements Parcelable, IdItem {

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

    private static final String DELIMITER = " ";

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "genre")
    private String genres;
    @Json(name = "fsk")
    private String fsk;
    @Json(name = "description")
    private String description;
    @Json(name = "medium")
    private String medium;
    @Json(name = "count")
    private int episodeAmount;
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

    private EntryCore() {
    }

    /**
     * Constructor of the entrycore data.
     *
     * @param id          The entry id.
     * @param name        The entry name.
     * @param genres       The genres.
     * @param fsk         The fsk ratings.
     * @param description The description.
     * @param medium      The medium.
     * @param episodeAmount       The number of episodes.
     * @param state       The user view state.
     * @param rateSum     The sum of all ratings.
     * @param rateCount   The amount of ratings.
     * @param clicks      The amount of clicks.
     * @param category    The category name.
     * @param license     The license id.
     */
    public EntryCore(@NonNull String id, @NonNull String name,
                     @NonNull @Genre String genres, @NonNull String fsk,
                     @NonNull String description, @NonNull @Medium String medium,
                     @IntRange(from = 1) int episodeAmount, @IntRange(from = 0) int state,
                     @IntRange(from = 0) int rateSum, @IntRange(from = 0) int rateCount,
                     @IntRange(from = 0) int clicks, @NonNull @Category String category,
                     @License int license) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.fsk = fsk;
        this.description = description;
        this.medium = medium;
        this.episodeAmount = episodeAmount;
        this.state = state;
        this.rateSum = rateSum;
        this.rateCount = rateCount;
        this.clicks = clicks;
        this.category = category;
        this.license = license;
    }

    protected EntryCore(Parcel in) {
        id = in.readString();
        name = in.readString();
        genres = in.readString();
        fsk = in.readString();
        description = in.readString();
        medium = in.readString();
        episodeAmount = in.readInt();
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
    @Override
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
     * Returns the genres of this entry.
     *
     * @return The genres.
     */
    @NonNull
    @Genre
    public String[] getGenres() {
        //noinspection ResourceType
        return genres.split(DELIMITER);
    }

    /**
     * Returns an array of fsk names of this entry.
     *
     * @return An array of fsk names.
     */
    @NonNull
    @FskParameter.FskConstraint
    public String[] getFsk() {
        //noinspection WrongConstant
        return fsk.split(DELIMITER);
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
    @Medium
    public String getMedium() {
        return medium;
    }

    /**
     * Get the overall amount of episodes/chapters of this entry. It does include not available
     * episodes.
     *
     * @return The amount of episodes/chapters.
     */
    @IntRange(from = 0)
    public int getEpisodeAmount() {
        return episodeAmount;
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
    @NonNull
    @Category
    public String getCategory() {
        return category;
    }

    /**
     * Returns the license state of this entry.
     *
     * @return The license state.
     */
    @License
    public int getLicense() {
        return license;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(genres);
        dest.writeString(fsk);
        dest.writeString(description);
        dest.writeString(medium);
        dest.writeInt(episodeAmount);
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
        if (episodeAmount != entryCore.episodeAmount) return false;
        if (state != entryCore.state) return false;
        if (rateSum != entryCore.rateSum) return false;
        if (rateCount != entryCore.rateCount) return false;
        if (clicks != entryCore.clicks) return false;
        if (!name.equals(entryCore.name)) return false;
        if (!genres.equals(entryCore.genres)) return false;
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
        result = 31 * result + genres.hashCode();
        result = 31 * result + fsk.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + episodeAmount;
        result = 31 * result + state;
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
        result = 31 * result + clicks;
        result = 31 * result + category.hashCode();
        result = 31 * result + license;
        return result;
    }
}
