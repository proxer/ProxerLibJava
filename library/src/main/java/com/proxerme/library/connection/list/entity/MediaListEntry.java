package com.proxerme.library.connection.list.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.GenreParameter.Genre;
import com.proxerme.library.parameters.MediumParameter.Medium;
import com.proxerme.library.parameters.StateParameter.State;
import com.proxerme.library.parameters.SubDubLanguageParameter.SubDubLanguage;
import com.squareup.moshi.Json;

/**
 * Entity holding all relevant info about a single entry in the media list (Anime, Manga)
 *
 * @author Ruben Gees
 */
public class MediaListEntry implements Parcelable, IdItem {

    public static final Parcelable.Creator<MediaListEntry> CREATOR = new Parcelable.Creator<MediaListEntry>() {
        @Override
        public MediaListEntry createFromParcel(Parcel source) {
            return new MediaListEntry(source);
        }

        @Override
        public MediaListEntry[] newArray(int size) {
            return new MediaListEntry[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "genre")
    private String genres;
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
    @Json(name = "language")
    private String languages;

    private MediaListEntry() {

    }

    /**
     * The constructor.
     *
     * @param id           The id of the entry.
     * @param name         The name.
     * @param genres       The genres, separated by a ' '.
     * @param medium       The medium.
     * @param episodeCount The amount of episodes.
     * @param state        The state.
     * @param rateSum      The sum of all ratings.
     * @param rateCount    The amount of ratings.
     * @param languages    The languages, separated by a ','.
     */
    public MediaListEntry(@NonNull String id, @NonNull String name, @NonNull String genres,
                          @NonNull @Medium String medium, @IntRange(from = 1) int episodeCount,
                          @State int state, @IntRange(from = 0) int rateSum,
                          @IntRange(from = 0) int rateCount, @NonNull String languages) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.medium = medium;
        this.episodeCount = episodeCount;
        this.state = state;
        this.rateSum = rateSum;
        this.rateCount = rateCount;
        this.languages = languages;
    }

    protected MediaListEntry(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.genres = in.readString();
        this.medium = in.readString();
        this.episodeCount = in.readInt();
        this.state = in.readInt();
        this.rateSum = in.readInt();
        this.rateCount = in.readInt();
        this.languages = in.readString();
    }

    /**
     * Returns the id of the entry.
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
     * Returns an array with the genres of this entry.
     *
     * @return The array of genres.
     */
    @SuppressWarnings("WrongConstant")
    @NonNull
    @Genre
    public String[] getGenres() {
        if (genres == null || genres.isEmpty()) {
            return new String[0];
        } else {
            return genres.split(" ");
        }
    }

    /**
     * Returns the medium of the entry.
     *
     * @return The medium.
     */
    @NonNull
    @Medium
    public String getMedium() {
        return medium;
    }

    /**
     * Returns the amount of episodes the entry has in total.
     *
     * @return The amount of episodes.
     */
    @IntRange(from = 1)
    public int getEpisodeCount() {
        return episodeCount;
    }

    /**
     * Returns the state of the entry.
     *
     * @return The state.
     */
    @State
    public int getState() {
        return state;
    }

    /**
     * Returns the sum of all ratings.
     *
     * @return The sum.
     */
    @IntRange(from = 0)
    public int getRateSum() {
        return rateSum;
    }

    /**
     * Returns the amount of ratings.
     *
     * @return The amount of ratings.
     */
    @IntRange(from = 0)
    public int getRateCount() {
        return rateCount;
    }

    /**
     * Returns the average rating.
     *
     * @return The average rating.
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
     * Returns an array with the available languages.
     *
     * @return An array with the languages.
     */
    @SuppressWarnings("WrongConstant")
    @NonNull
    @SubDubLanguage
    public String[] getLanguages() {
        if (languages == null || languages.isEmpty()) {
            return new String[0];
        } else {
            return languages.split(",");
        }
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaListEntry that = (MediaListEntry) o;

        if (episodeCount != that.episodeCount) return false;
        if (state != that.state) return false;
        if (rateSum != that.rateSum) return false;
        if (rateCount != that.rateCount) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!genres.equals(that.genres)) return false;
        if (!medium.equals(that.medium)) return false;
        return languages.equals(that.languages);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + episodeCount;
        result = 31 * result + state;
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
        result = 31 * result + languages.hashCode();
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
        dest.writeString(this.medium);
        dest.writeInt(this.episodeCount);
        dest.writeInt(this.state);
        dest.writeInt(this.rateSum);
        dest.writeInt(this.rateCount);
        dest.writeString(this.languages);
    }
}
