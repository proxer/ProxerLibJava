package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.list.IndustryCore;
import me.proxer.library.enums.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Entity holding all info of an entry. This is everything what {@link EntryCore} contains and some
 * more which could also be retrieved by the respective standalone APIs.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class Entry implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the genres.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "genre")
    private Set<Genre> genres;

    /**
     * Returns the fsk ratings.
     */
    @Json(name = "fsk")
    @Getter(onMethod = @__({@NotNull}))
    private Set<FskConstraint> fskConstraints;

    /**
     * Returns the description.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "description")
    private String description;

    /**
     * Returns the medium
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the amount of episodes, this entry has. They do not necessarily have to be uploaded.
     */
    @Json(name = "count")
    private int episodeAmount;

    /**
     * Returns the current state.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "state")
    private MediaState state;

    /**
     * Returns the sum of all ratings.
     */
    @Json(name = "rate_sum")
    private int ratingSum;

    /**
     * Returns the amount of ratings.
     */
    @Json(name = "rate_count")
    private int ratingAmount;

    /**
     * Returns the clicks on this entry, in this season.
     */
    @Json(name = "clicks")
    private int clicks;

    /**
     * Returns the category.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the type of license.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "license")
    private License license;

    /**
     * Returns true, if this entry is age restricted.
     */
    @Json(name = "ageRestricted")
    private boolean ageRestricted;

    /**
     * Returns the synonyms in different languages.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "names")
    private List<Synonym> synonyms;

    /**
     * Returns the languages, this entry is available in.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "lang")
    private Set<MediaLanguage> languages;

    /**
     * Returns the seasons, this entry was aired. If present, the first in the list is the start and the second the end.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "seasons")
    private List<EntrySeasonInfo> seasons;

    /**
     * Returns the translator groups.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "groups")
    private List<EntryTranslatorGroup> translatorGroups;

    /**
     * Returns the industries involved in production and marketing.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "publisher")
    private List<IndustryCore> industries;

    /**
     * Returns the tags.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "tags")
    private List<Tag> tags;

    /**
     * Returns the average of all ratings.
     */
    public float getRating() {
        if (ratingAmount <= 0) {
            return 0;
        } else {
            return ratingSum / ratingAmount;
        }
    }
}
