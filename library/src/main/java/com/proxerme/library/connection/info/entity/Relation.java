package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.FskParameter;
import com.proxerme.library.parameters.GenreParameter;
import com.proxerme.library.parameters.LanguageParameter;
import com.proxerme.library.parameters.LicenseParameter;
import com.proxerme.library.parameters.MediumParameter;
import com.proxerme.library.parameters.SeasonParameter;
import com.proxerme.library.parameters.StateParameter;
import com.squareup.moshi.Json;

/**
 * Representation of a related anime/manga to the current entry.
 *
 * @author Desnoo
 */
public class Relation implements Parcelable, IdItem {

    public static final Creator<Relation> CREATOR = new Creator<Relation>() {
        @Override
        public Relation createFromParcel(Parcel in) {
            return new Relation(in);
        }

        @Override
        public Relation[] newArray(int size) {
            return new Relation[size];
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
    private String language;
    @Json(name = "year")
    private int year;
    @Json(name = "season")
    private int season;

    /**
     * Private constructor used by moshi.
     */
    private Relation() {
    }

    /**
     * The constructor.
     *
     * @param id The relation id.
     * @param name The name.
     * @param genre The genre.
     * @param fsk The fsk rating.
     * @param description The description.
     * @param medium The medium.
     * @param episodeCount The count of episodes/chapters.
     * @param state The state of the anime/manga.
     * @param rateSum The sum of all ratings.
     * @param rateCount The count of all ratings.
     * @param clicks The count of clicks on this entry.
     * @param category The type.
     * @param license The license state.
     * @param language The language.
     * @param year The release year.
     * @param season The release season.
     */
    public Relation(@NonNull String id, @NonNull String name, @GenreParameter.Genre String genre,
                    @FskParameter.FskConstraint String fsk, @NonNull String description,
                    @MediumParameter.Medium String medium, @IntRange(from = 0) int episodeCount,
                    @StateParameter.State int state, @IntRange(from = 0) int rateSum, @IntRange(from = 0) int rateCount,
                    @IntRange(from = 0) int clicks, @CategoryParameter.Category String category,
                    @LicenseParameter.License int license,
                    @LanguageParameter.Language String language, @IntRange(from = 0) int year,
                    @SeasonParameter.SeasonConstraint int season) {
        this.id = id;
        this.name = name;
        this.genre = genre;
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
        this.language = language;
        this.year = year;
        this.season = season;
    }

    /**
     * Parse relation from parcel.
     *
     * @param in The parcel to parse.
     */
    protected Relation(Parcel in) {
        id = in.readString();
        name = in.readString();
        genre = in.readString();
        fsk = in.readString();
        description = in.readString();
        medium = in.readString();
        episodeCount = in.readInt();
        state = in.readInt();
        rateSum = in.readInt();
        rateCount = in.readInt();
        clicks = in.readInt();
        category = in.readString();
        license = in.readInt();
        language = in.readString();
        year = in.readInt();
        season = in.readInt();
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
     * Returns the Genre.
     *
     * @return The Genre.
     **/
    @GenreParameter.Genre
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the Fsk.
     *
     * @return The Fsk.
     **/
    @FskParameter.FskConstraint
    public String getFsk() {
        return fsk;
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
    @MediumParameter.Medium
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
    @StateParameter.State
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
    @CategoryParameter.Category
    public String getCategory() {
        return category;
    }

    /**
     * Returns the License.
     *
     * @return The License.
     **/
    @LicenseParameter.License
    public int getLicense() {
        return license;
    }

    /**
     * Returns the Language.
     *
     * @return The Language.
     **/
    @LanguageParameter.Language
    public String getLanguage() {
        return language;
    }

    /**
     * Returns the Year.
     *
     * @return The Year.
     **/
    @IntRange(from = 0)
    public int getYear() {
        return year;
    }

    /**
     * Returns the Season.
     *
     * @return The Season.
     **/
    @SeasonParameter.SeasonConstraint
    public int getSeason() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(genre);
        parcel.writeString(fsk);
        parcel.writeString(description);
        parcel.writeString(medium);
        parcel.writeInt(episodeCount);
        parcel.writeInt(state);
        parcel.writeInt(rateSum);
        parcel.writeInt(rateCount);
        parcel.writeInt(clicks);
        parcel.writeString(category);
        parcel.writeInt(license);
        parcel.writeString(language);
        parcel.writeInt(year);
        parcel.writeInt(season);
    }


    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (episodeCount != relation.episodeCount) return false;
        if (state != relation.state) return false;
        if (rateCount != relation.rateCount) return false;
        if (clicks != relation.clicks) return false;
        if (license != relation.license) return false;
        if (year != relation.year) return false;
        if (season != relation.season) return false;
        if (rateSum != relation.rateSum) return false;
        if (!id.equals(relation.id)) return false;
        if (!name.equals(relation.name)) return false;
        if (!genre.equals(relation.genre)) return false;
        if (!fsk.equals(relation.fsk)) return false;
        if (!description.equals(relation.description)) return false;
        if (!medium.equals(relation.medium)) return false;
        return category.equals(relation.category) && language.equals(relation.language);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genre.hashCode();
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
        result = 31 * result + language.hashCode();
        result = 31 * result + year;
        result = 31 * result + season;
        return result;
    }
}
