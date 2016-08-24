package com.proxerme.library.connection.info.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.parameters.SeasonParameter.SeasonConstraint;
import com.proxerme.library.parameters.TypeParameter.Type;
import com.squareup.moshi.Json;

/**
 * Entity holding the seasons of a entry.
 *
 * @author Desnoo
 */
public class Season implements Parcelable, IdItem {

    public static final Creator<Season> CREATOR = new Creator<Season>() {
        @Override
        public Season createFromParcel(Parcel in) {
            return new Season(in);
        }

        @Override
        public Season[] newArray(int size) {
            return new Season[size];
        }
    };

    @Json(name = "id")
    private String id;
    @Json(name = "eid")
    private String entryId;
    @Json(name = "type")
    private String type;
    @Json(name = "year")
    private int year;
    @Json(name = "season")
    private int season;

    private Season() {
    }

    /**
     * The Constructor of a Season.
     *
     * @param id      The entry id.
     * @param entryId The id of an entry.(Anime, Manga)
     * @param type    The type of the entry.
     * @param year    The year when the entry was released.
     * @param season  The season in which the entry was released.
     */
    public Season(@NonNull String id, @NonNull String entryId, @Type String type,
                  @IntRange(from = 1900) int year, @SeasonConstraint int season) {
        this.id = id;
        this.entryId = entryId;
        this.type = type;
        this.year = year;
        this.season = season;
    }

    protected Season(Parcel in) {
        id = in.readString();
        entryId = in.readString();
        type = in.readString();
        year = in.readInt();
        season = in.readInt();
    }

    /**
     * Returns the id of this name.
     *
     * @return The id.
     */
    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the corresponding entry(Anime/Manga id) of this entry.
     *
     * @return The id of the corresponding entry.
     */
    @NonNull
    public String getEntryId() {
        return entryId;
    }

    /**
     * Returns the type of this entry.
     *
     * @return The type.
     */
    @NonNull
    @Type
    public String getType() {
        return type;
    }

    /**
     * Returns the year of this season.
     *
     * @return The year of the season.
     */
    @IntRange(from = 1900)
    public int getYear() {
        return year;
    }

    /**
     * Returns the season.
     *
     * @return The season. (Spring, Summer, Autumn, Winter)
     */
    @SeasonConstraint
    public int getSeason() {
        return season;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(entryId);
        parcel.writeString(type);
        parcel.writeInt(year);
        parcel.writeInt(season);
    }

}
