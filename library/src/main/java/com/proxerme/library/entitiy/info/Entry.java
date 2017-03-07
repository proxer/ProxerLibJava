package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.*;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Entity holding all info of an entry. This is everything what {@link EntryCore} contains and some
 * more which could also be retrieved by their standalone APIs.
 *
 * @author Ruben Gees
 */
public class Entry implements IdItem {

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
    @Json(name = "gate")
    private boolean gate;
    @Json(name = "names")
    private List<Synonym> synonyms;
    @Json(name = "lang")
    private Set<MediaLanguage> languages;
    @Json(name = "seasons")
    private List<EntrySeasonInfo> seasons;
    @Json(name = "groups")
    private List<EntryTranslatorGroup> translatorGroups;
    @Json(name = "publisher")
    private List<EntryIndustry> industries;
    @Json(name = "tags")
    private List<Tag> tags;

    private Entry() {

    }

    /**
     * The constructor.
     *
     * @param id               The entry id.
     * @param name             The entry name.
     * @param genres           The genres.
     * @param fskConstraints   The fsk ratings.
     * @param description      The description.
     * @param medium           The medium.
     * @param episodeAmount    The number of episodes.
     * @param state            The user view state.
     * @param rateSum          The sum of all ratings.
     * @param rateCount        The amount of ratings.
     * @param clicks           The amount of clicks.
     * @param category         The category name.
     * @param license          The license id.
     * @param gate             If this entry has a gate (18+ check required)
     * @param synonyms         The synonyms.
     * @param languages        The languages.
     * @param seasons          The seasons.
     * @param translatorGroups The translator groups.
     * @param industries       The industries.
     * @param tags             The tags.
     */
    public Entry(@NotNull final String id, @NotNull final String name, @NotNull final Set<Genre> genres,
                 @NotNull final Set<FskConstraint> fskConstraints, @NotNull final String description,
                 @NotNull final Medium medium, final int episodeAmount, @NotNull final MediaState state,
                 final int rateSum, final int rateCount, final int clicks, @NotNull final Category category,
                 @NotNull final Licence license, final boolean gate, @NotNull final List<Synonym> synonyms,
                 @NotNull final Set<MediaLanguage> languages, @NotNull final List<EntrySeasonInfo> seasons,
                 @NotNull final List<EntryTranslatorGroup> translatorGroups,
                 @NotNull final List<EntryIndustry> industries, @NotNull final List<Tag> tags) {
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
        this.gate = gate;
        this.synonyms = synonyms;
        this.languages = languages;
        this.seasons = seasons;
        this.translatorGroups = translatorGroups;
        this.industries = industries;
        this.tags = tags;
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
    @NotNull
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
    @NotNull
    public Licence getLicense() {
        return license;
    }

    /**
     * Returns if this entry has a gate (18+ check needed).
     *
     * @return True, if gated.
     */
    public boolean isGate() {
        return gate;
    }

    /**
     * Returns the synonyms.
     *
     * @return The synonyms.
     */
    @NotNull
    public List<Synonym> getSynonyms() {
        return synonyms;
    }

    /**
     * Returns the available languages.
     *
     * @return The languages.
     */
    @NotNull
    public Set<MediaLanguage> getLanguages() {
        return languages;
    }

    /**
     * Returns the seasons.
     *
     * @return The seasons.
     */
    @NotNull
    public List<EntrySeasonInfo> getSeasons() {
        return seasons;
    }

    /**
     * Returns the translator groups.
     *
     * @return The translator groups.
     */
    @NotNull
    public List<EntryTranslatorGroup> getTranslatorGroups() {
        return translatorGroups;
    }

    /**
     * Returns the industries.
     *
     * @return The industries.
     */
    @NotNull
    public List<EntryIndustry> getIndustries() {
        return industries;
    }

    /**
     * Returns all the tags.
     *
     * @return The tags.
     */
    @NotNull
    public List<Tag> getTags() {
        return tags;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Entry entry = (Entry) o;

        if (episodeAmount != entry.episodeAmount) return false;
        if (rateSum != entry.rateSum) return false;
        if (rateCount != entry.rateCount) return false;
        if (clicks != entry.clicks) return false;
        if (gate != entry.gate) return false;
        if (!id.equals(entry.id)) return false;
        if (!name.equals(entry.name)) return false;
        if (!genres.equals(entry.genres)) return false;
        if (!fskConstraints.equals(entry.fskConstraints)) return false;
        if (!description.equals(entry.description)) return false;
        if (medium != entry.medium) return false;
        if (state != entry.state) return false;
        if (category != entry.category) return false;
        if (license != entry.license) return false;
        if (!synonyms.equals(entry.synonyms)) return false;
        if (!languages.equals(entry.languages)) return false;
        if (!seasons.equals(entry.seasons)) return false;
        if (!translatorGroups.equals(entry.translatorGroups)) return false;
        if (!industries.equals(entry.industries)) return false;
        return tags.equals(entry.tags);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genres.hashCode();
        result = 31 * result + fskConstraints.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + medium.hashCode();
        result = 31 * result + episodeAmount;
        result = 31 * result + state.hashCode();
        result = 31 * result + rateSum;
        result = 31 * result + rateCount;
        result = 31 * result + clicks;
        result = 31 * result + category.hashCode();
        result = 31 * result + license.hashCode();
        result = 31 * result + (gate ? 1 : 0);
        result = 31 * result + synonyms.hashCode();
        result = 31 * result + languages.hashCode();
        result = 31 * result + seasons.hashCode();
        result = 31 * result + translatorGroups.hashCode();
        result = 31 * result + industries.hashCode();
        result = 31 * result + tags.hashCode();
        return result;
    }
}
