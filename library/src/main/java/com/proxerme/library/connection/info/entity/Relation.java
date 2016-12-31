package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.parameters.FskParameter;
import com.proxerme.library.parameters.GeneralLanguageParameter;
import com.proxerme.library.parameters.GenreParameter;
import com.proxerme.library.parameters.LicenseParameter.License;
import com.proxerme.library.parameters.MediumParameter.Medium;
import com.proxerme.library.parameters.SeasonParameter.SeasonConstraint;
import com.proxerme.library.parameters.StateParameter.State;
import com.squareup.moshi.Json;

/**
 * Representation of a related anime/manga to the current entry.
 *
 * @author Desnoo
 */
public class Relation implements IdItem, Parcelable {

    public static final Parcelable.Creator<Relation> CREATOR = new Parcelable.Creator<Relation>() {
        @Override
        public Relation createFromParcel(Parcel source) {
            return new Relation(source);
        }

        @Override
        public Relation[] newArray(int size) {
            return new Relation[size];
        }
    };

    private static final String LANGUAGE_DELIMITER = ",";
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
    private int episodeCount;
    @Json(name = "state")
    private int state;
    @Json(name = "rate_sum")
    private int rateSum;
    @Json(name = "rate_count")
    private int rateCount;
    @Json(name = "clicks")
    private int clicks;
    @Json(name = "kat")
    private String category;
    @Json(name = "license")
    private int license;
    @Json(name = "language")
    private String languages;
    @Json(name = "year")
    private Integer year;
    @Json(name = "season")
    private Integer season;

    /**
     * Private constructor used by moshi.
     */
    private Relation() {
    }


    /**
     * The constructor.
     *
     * @param id           The relation id.
     * @param name         The name.
     * @param genre        The genres.
     * @param fsk          The fsk rating.
     * @param description  The description.
     * @param medium       The medium.
     * @param episodeCount The count of episodes/chapters.
     * @param state        The state of the anime/manga.
     * @param rateSum      The sum of all ratings.
     * @param rateCount    The count of all ratings.
     * @param clicks       The count of clicks on this entry.
     * @param category     The type.
     * @param license      The license state.
     * @param languages    The languages.
     * @param year         The release year.
     * @param season       The release season.
     */
    public Relation(@NonNull String id, @NonNull String name, @NonNull String genre,
                    @NonNull String fsk, @NonNull String description,
                    @Medium String medium, @IntRange(from = 0) int episodeCount,
                    @State int state, @IntRange(from = 0) int rateSum,
                    @IntRange(from = 0) int rateCount, @IntRange(from = 0) int clicks,
                    @NonNull @Category String category, @License int license,
                    @NonNull String languages, @IntRange(from = 0) @Nullable Integer year,
                    @SeasonConstraint @Nullable Integer season) {
        this.id = id;
        this.name = name;
        this.genres = genre;
        this.fsk = fsk;
        this.description = description;
        this.medium = medium;
        this.episodeCount = episodeCount;
        this.state = state;
        this.rateSum = rateSum;
        this.rateCount = rateCount;
        this.clicks = clicks;
        this.category = category;
        this.license = license;
        this.languages = languages;
        this.year = year;
        this.season = season;
    }

    protected Relation(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.genres = in.readString();
        this.fsk = in.readString();
        this.description = in.readString();
        this.medium = in.readString();
        this.episodeCount = in.readInt();
        this.state = in.readInt();
        this.rateSum = in.readInt();
        this.rateCount = in.readInt();
        this.clicks = in.readInt();
        this.category = in.readString();
        this.license = in.readInt();
        this.languages = in.readString();
        this.year = (Integer) in.readValue(Integer.class.getClassLoader());
        this.season = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    /**
     * Returns the Id.
     *
     * @return The Id.
     **/
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the Name.
     *
     * @return The Name.
     **/
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the genres of this relation.
     *
     * @return The genres.
     */
    @SuppressWarnings("WrongConstant")
    @NonNull
    @GenreParameter.Genre
    public String[] getGenres() {
        if (genres == null || genres.isEmpty()) {
            return new String[0];
        } else {
            return genres.split(DELIMITER);
        }
    }

    /**
     * Returns the Fsk.
     *
     * @return The Fsk.
     **/
    @SuppressWarnings("WrongConstant")
    @NonNull
    @FskParameter.FskConstraint
    public String[] getFsk() {
        if (fsk == null || fsk.isEmpty()) {
            return new String[0];
        } else {
            return fsk.split(DELIMITER);
        }
    }

    /**
     * Returns the Description.
     *
     * @return The Description.
     **/
    @NonNull
    public String getDescription() {
        return description;
    }

    /**
     * Returns the Medium.
     *
     * @return The Medium.
     **/
    @Medium
    public String getMedium() {
        return medium;
    }

    /**
     * Returns the Count.
     *
     * @return The Count.
     **/
    @IntRange(from = 0)
    public int getEpisodeCount() {
        return episodeCount;
    }

    /**
     * Returns the State.
     *
     * @return The State.
     **/
    @State
    public int getState() {
        return state;
    }

    /**
     * Returns the Rate_sum.
     *
     * @return The Rate_sum.
     **/
    @IntRange(from = 0)
    public int getRateSum() {
        return rateSum;
    }

    /**
     * Returns the Rate_count.
     *
     * @return The Rate_count.
     **/
    @IntRange(from = 0)
    public int getRateCount() {
        return rateCount;
    }

    /**
     * Returns the Clicks.
     *
     * @return The Clicks.
     **/
    @IntRange(from = 0)
    public int getClicks() {
        return clicks;
    }

    /**
     * Returns the Kat.
     *
     * @return The Kat.
     **/
    @Category
    public String getCategory() {
        return category;
    }

    /**
     * Returns the License.
     *
     * @return The License.
     **/
    @License
    public int getLicense() {
        return license;
    }

    /**
     * Returns the Language.
     *
     * @return The Language.
     **/
    @SuppressWarnings("WrongConstant")
    @NonNull
    @GeneralLanguageParameter.GeneralLanguage
    public String[] getLanguages() {
        if (languages == null || languages.isEmpty()) {
            return new String[0];
        } else {
            return languages.split(LANGUAGE_DELIMITER);
        }
    }

    /**
     * Returns the Year.
     *
     * @return The Year.
     **/
    @Nullable
    @IntRange(from = 0)
    public Integer getYear() {
        return year;
    }

    /**
     * Returns the Season.
     *
     * @return The Season.
     **/
    @Nullable
    @SeasonConstraint
    public Integer getSeason() {
        return season;
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

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (episodeCount != relation.episodeCount) return false;
        if (state != relation.state) return false;
        if (rateSum != relation.rateSum) return false;
        if (rateCount != relation.rateCount) return false;
        if (clicks != relation.clicks) return false;
        if (license != relation.license) return false;
        if (season != null ? season.equals(relation.season) : relation.season == null) return false;
        if (!id.equals(relation.id)) return false;
        if (!name.equals(relation.name)) return false;
        if (!genres.equals(relation.genres)) return false;
        if (!fsk.equals(relation.fsk)) return false;
        if (!description.equals(relation.description)) return false;
        if (!medium.equals(relation.medium)) return false;
        if (!category.equals(relation.category)) return false;
        if (!languages.equals(relation.languages)) return false;
        return year != null ? year.equals(relation.year) : relation.year == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + fsk.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + episodeCount;
        result = 31 * result + state;
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
        result = 31 * result + clicks;
        result = 31 * result + category.hashCode();
        result = 31 * result + license;
        result = 31 * result + languages.hashCode();
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
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
        dest.writeString(this.genres);
        dest.writeString(this.fsk);
        dest.writeString(this.description);
        dest.writeString(this.medium);
        dest.writeInt(this.episodeCount);
        dest.writeInt(this.state);
        dest.writeInt(this.rateSum);
        dest.writeInt(this.rateCount);
        dest.writeInt(this.clicks);
        dest.writeString(this.category);
        dest.writeInt(this.license);
        dest.writeString(this.languages);
        dest.writeValue(this.year);
        dest.writeValue(this.season);
    }
}
