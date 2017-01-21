package com.proxerme.library.connection.list.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.FskParameter;
import com.proxerme.library.parameters.GenreParameter.Genre;
import com.proxerme.library.parameters.MediumParameter.Medium;
import com.proxerme.library.parameters.ProjectTypeParameter.ProjectType;
import com.proxerme.library.parameters.StateParameter.State;
import com.squareup.moshi.Json;

/**
 * Entity representing a project of either a
 * {@link com.proxerme.library.connection.info.entity.TranslatorGroup} or a
 * {@link com.proxerme.library.connection.info.entity.Industry}.
 *
 * @author Ruben Gees
 */
public class ProjectListEntry implements Parcelable, IdItem {

    public static final Creator<ProjectListEntry> CREATOR = new Creator<ProjectListEntry>() {
        @Override
        public ProjectListEntry createFromParcel(Parcel source) {
            return new ProjectListEntry(source);
        }

        @Override
        public ProjectListEntry[] newArray(int size) {
            return new ProjectListEntry[size];
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
    @Json(name = "medium")
    private String medium;
    @Json(name = "type")
    private int type;
    @Json(name = "state")
    private int state;
    @Json(name = "rate_sum")
    private int rateSum;
    @Json(name = "rate_count")
    private int rateCount;

    private ProjectListEntry() {

    }

    /**
     * The constructor.
     *
     * @param id        The id.
     * @param name      The name.
     * @param genres    The genres, separated by a ' '.
     * @param fsk       The fsk ratings.
     * @param medium    The medium.
     * @param type      The type of the project.
     * @param state     The state.
     * @param rateSum   The sum of all ratings.
     * @param rateCount The amount of ratings.
     */
    public ProjectListEntry(@NonNull String id, @NonNull String name, @NonNull String genres,
                            @NonNull String fsk, @NonNull @Medium String medium,
                            @ProjectType int type, @State int state,
                            @IntRange(from = 0) int rateSum, @IntRange(from = 0) int rateCount) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.fsk = fsk;
        this.medium = medium;
        this.type = type;
        this.state = state;
        this.rateSum = rateSum;
        this.rateCount = rateCount;
    }

    protected ProjectListEntry(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.genres = in.readString();
        this.fsk = in.readString();
        this.medium = in.readString();
        this.type = in.readInt();
        this.state = in.readInt();
        this.rateSum = in.readInt();
        this.rateCount = in.readInt();
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
     * Returns an array with the genres of this project.
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
     * Returns the medium.
     *
     * @return The medium.
     */
    @NonNull
    @Medium
    public String getMedium() {
        return medium;
    }

    /**
     * Returns the type of the project.
     *
     * @return The type.
     */
    @ProjectType
    public int getType() {
        return type;
    }

    /**
     * Returns the state.
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

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectListEntry that = (ProjectListEntry) o;

        if (type != that.type) return false;
        if (state != that.state) return false;
        if (rateSum != that.rateSum) return false;
        if (rateCount != that.rateCount) return false;
        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!genres.equals(that.genres)) return false;
        if (!fsk.equals(that.fsk)) return false;
        return medium.equals(that.medium);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + fsk.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + type;
        result = 31 * result + state;
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
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
        dest.writeString(this.medium);
        dest.writeInt(this.type);
        dest.writeInt(this.state);
        dest.writeInt(this.rateSum);
        dest.writeInt(this.rateCount);
    }
}
