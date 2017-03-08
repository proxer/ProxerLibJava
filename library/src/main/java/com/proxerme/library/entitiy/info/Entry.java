package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.*;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Entity holding all info of an entry. This is everything what {@link EntryCore} contains and some
 * more which could also be retrieved by their standalone APIs.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class Entry implements IdItem {

    /**
     * @return The id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * @return The name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String name;

    /**
     * @return The genres.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "genre")
    private Set<Genre> genres;

    /**
     * @return The fsk ratings.
     */
    @Json(name = "fsk")
    @Getter(onMethod = @__({@NotNull}))
    private Set<FskConstraint> fskConstraints;

    /**
     * @return The description.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "description")
    private String description;

    /**
     * @return The medium
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * @return The amount of episodes, this entry has. They do not necessarily have to be uploaded.
     */
    @Json(name = "count")
    private int episodeAmount;

    /**
     * @return The current state.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "state")
    private MediaState state;

    /**
     * @return The sum of all ratings.
     */
    @Json(name = "rate_sum")
    private int ratingSum;

    /**
     * @return The amount of ratings.
     */
    @Json(name = "rate_count")
    private int ratingAmount;

    /**
     * @return The clicks on this entry, in this season.
     */
    @Json(name = "clicks")
    private int clicks;

    /**
     * @return The category.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "kat")
    private Category category;

    /**
     * @return The type of licence.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "license")
    private Licence licence;

    /**
     * @return True, if this entry is age restricted.
     */
    @Json(name = "ageRestricted")
    private boolean ageRestricted;

    /**
     * @return The synonyms in different languages.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "names")
    private List<Synonym> synonyms;

    /**
     * @return The languages, this entry is available in.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "lang")
    private Set<MediaLanguage> languages;

    /**
     * @return The seasons, this entry was aired. If present, the first in the list is the start and the second the end.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "seasons")
    private List<EntrySeasonInfo> seasons;

    /**
     * @return The translator groups.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "groups")
    private List<EntryTranslatorGroup> translatorGroups;

    /**
     * @return The industries involved in production and marketing.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "publisher")
    private List<EntryIndustry> industries;

    /**
     * @return The tags.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "tags")
    private List<Tag> tags;

    /**
     * @return The sum of all ratings.
     */
    public float getRating() {
        if (ratingAmount <= 0) {
            return 0;
        } else {
            return ratingSum / ratingAmount;
        }
    }
}
