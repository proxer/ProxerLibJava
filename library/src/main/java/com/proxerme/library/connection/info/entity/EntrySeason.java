package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.parameters.SeasonParameter.SeasonConstraint;
import com.squareup.moshi.Json;

/**
 * Entity holding the season of an entry. This is almost the same as {@link Season} but contains
 * less info.
 *
 * @author Ruben Gees
 */
public class EntrySeason implements Parcelable {

    public static final Parcelable.Creator<EntrySeason> CREATOR = new Parcelable.Creator<EntrySeason>() {
        @Override
        public EntrySeason createFromParcel(Parcel source) {
            return new EntrySeason(source);
        }

        @Override
        public EntrySeason[] newArray(int size) {
            return new EntrySeason[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "year")
    private int year;
    @Json(name = "season")
    private int season;

    private EntrySeason() {

    }

    /**
     * The constructor.
     *
     * @param id     The id.
     * @param year   The year.
     * @param season The actual season.
     */
    public EntrySeason(@NonNull String id, @IntRange(from = 1900) int year,
                       @SeasonConstraint int season) {
        this.id = id;
        this.year = year;
        this.season = season;
    }

    protected EntrySeason(Parcel in) {
        this.id = in.readString();
        this.year = in.readInt();
        this.season = in.readInt();
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
     * Returns the year.
     *
     * @return The year.
     */
    @IntRange(from = 1900)
    public int getYear() {
        return year;
    }

    /**
     * Returns the season.
     *
     * @return The season.
     */
    @SeasonConstraint
    public int getSeason() {
        return season;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntrySeason that = (EntrySeason) o;

        if (year != that.year) return false;
        if (season != that.season) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + year;
        result = 31 * result + season;
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.year);
        dest.writeInt(this.season);
    }
}
