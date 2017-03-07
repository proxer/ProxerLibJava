package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.Season;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding the season of an entry.
 *
 * @author Ruben Gees
 */
public class EntrySeasonInfo {

    @Json(name = "id")
    private String id;
    @Json(name = "year")
    private int year;
    @Json(name = "season")
    private Season season;

    private EntrySeasonInfo() {

    }

    /**
     * The constructor.
     *
     * @param id     The id.
     * @param year   The year.
     * @param season The actual season.
     */
    public EntrySeasonInfo(@NotNull final String id, final int year, final Season season) {
        this.id = id;
        this.year = year;
        this.season = season;
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Returns the year.
     *
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the season.
     *
     * @return The season.
     */
    public Season getSeason() {
        return season;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final EntrySeasonInfo that = (EntrySeasonInfo) o;

        if (year != that.year) return false;
        if (!id.equals(that.id)) return false;
        return season == that.season;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + year;
        result = 31 * result + season.hashCode();
        return result;
    }
}
