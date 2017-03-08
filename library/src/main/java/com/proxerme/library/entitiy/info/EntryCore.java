package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.*;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Entity holding the detail data of an Entry (Anime, Manga)
 *
 * @author Desnoo
 */
public final class EntryCore implements IdItem {

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "genre")
    private Set<Genre> genres;
    @Json(name = "fsk")
    private Set<FskConstraint> fskConstraints;
    @Json(name = "description")
    private String description;
    @Json(name = "medium")
    private Medium medium;
    @Json(name = "count")
    private int episodeAmount;
    @Json(name = "state")
    private MediaState state;
    @Json(name = "rate_sum")
    private int rateSum;
    @Json(name = "rate_count")
    private int rateCount;
    @Json(name = "clicks")
    private int clicks;
    @Json(name = "kat")
    private Category category;
    @Json(name = "license")
    private Licence license;

    private EntryCore() {

    }

    /**
     * Constructor of the entrycore data.
     *
     * @param id             The entry id.
     * @param name           The entry name.
     * @param genres         The genres.
     * @param fskConstraints The fsk ratings.
     * @param description    The description.
     * @param medium         The medium.
     * @param episodeAmount  The number of episodes.
     * @param state          The user view state.
     * @param rateSum        The sum of all ratings.
     * @param rateCount      The amount of ratings.
     * @param clicks         The amount of clicks.
     * @param category       The category name.
     * @param license        The license id.
     */
    public EntryCore(@NotNull final String id, @NotNull final String name, @NotNull final Set<Genre> genres,
                     @NotNull final Set<FskConstraint> fskConstraints, @NotNull final String description,
                     @NotNull final Medium medium, final int episodeAmount, @NotNull final MediaState state,
                     final int rateSum, final int rateCount, final int clicks, @NotNull final Category category,
                     @NotNull final Licence license) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.fskConstraints = fskConstraints;
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

    /**
     * Returns the id of this entry.
     *
     * @return The id.
     */
    @NotNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the name of this entry.
     *
     * @return the name.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Returns the genres of this entry.
     *
     * @return The genres.
     */
    @NotNull
    public Set<Genre> getGenres() {
        return genres;
    }

    /**
     * Returns an array of fsk names of this entry.
     *
     * @return An array of fsk names.
     */
    @NotNull
    public Set<FskConstraint> getFskConstraints() {
        return fskConstraints;
    }

    /**
     * Returns the description of this entry.
     *
     * @return The description.
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    /**
     * Returns the medium of this entry.
     *
     * @return The medium.
     */
    @NotNull
    public Medium getMedium() {
        return medium;
    }

    /**
     * Get the overall amount of episodes/chapters of this entry. It does include not available
     * episodes.
     *
     * @return The amount of episodes/chapters.
     */
    public int getEpisodeAmount() {
        return episodeAmount;
    }

    /**
     * Returns the view state for the current user of this entry. If no user is set then this is 0.
     *
     * @return The view state for the current user.
     */
    public MediaState getState() {
        return state;
    }

    /**
     * Returns the sum of all ratings of this entry.
     *
     * @return The sum of ratings.
     */
    public int getRateSum() {
        return rateSum;
    }

    /**
     * Returns the amount of ratings of this entry.
     *
     * @return The amount of ratings.
     */
    public int getRateCount() {
        return rateCount;
    }

    /**
     * Returns the sum of all ratings of this entry.
     *
     * @return The sum of all ratings.
     */
    public float getRating() {
        if (rateCount <= 0) {
            return 0;
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
    @NotNull
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the license state of this entry.
     *
     * @return The license state.
     */
    public Licence getLicense() {
        return license;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryCore entryCore = (EntryCore) o;

        if (episodeAmount != entryCore.episodeAmount) return false;
        if (rateSum != entryCore.rateSum) return false;
        if (rateCount != entryCore.rateCount) return false;
        if (clicks != entryCore.clicks) return false;
        if (!id.equals(entryCore.id)) return false;
        if (!name.equals(entryCore.name)) return false;
        if (genres != null ? !genres.equals(entryCore.genres) : entryCore.genres != null) return false;
        if (fskConstraints != null ? !fskConstraints.equals(entryCore.fskConstraints) : entryCore.fskConstraints != null)
            return false;
        if (!description.equals(entryCore.description)) return false;
        if (medium != entryCore.medium) return false;
        if (state != entryCore.state) return false;
        if (category != entryCore.category) return false;
        return license == entryCore.license;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (fskConstraints != null ? fskConstraints.hashCode() : 0);
        result = 31 * result + description.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + episodeAmount;
        result = 31 * result + state.hashCode();
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
        result = 31 * result + clicks;
        result = 31 * result + category.hashCode();
        result = 31 * result + license.hashCode();
        return result;
    }
}
