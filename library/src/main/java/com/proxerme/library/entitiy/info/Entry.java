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
     * @return The id of this entry:
     */
    @Getter(onMethod = @__(@NotNull))
    @Json(name = "id")
    private String id;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "name")
    private String name;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "genre")
    private Set<Genre> genres;
    @Json(name = "fsk")

    @Getter(onMethod = @__(@NotNull))
    private Set<FskConstraint> fskConstraints;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "description")
    private String description;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "medium")
    private Medium medium;

    @Json(name = "count")
    private int episodeAmount;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "state")
    private MediaState state;

    @Json(name = "rate_sum")
    private int rateSum;

    @Json(name = "rate_count")
    private int rateCount;

    @Json(name = "clicks")
    private int clicks;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "kat")
    private Category category;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "license")
    private Licence license;

    @Json(name = "ageRestricted")
    private boolean ageRestricted;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "names")
    private List<Synonym> synonyms;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "lang")
    private Set<MediaLanguage> languages;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "seasons")
    private List<EntrySeasonInfo> seasons;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "groups")
    private List<EntryTranslatorGroup> translatorGroups;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "publisher")
    private List<EntryIndustry> industries;

    @Getter(onMethod = @__(@NotNull))
    @Json(name = "tags")
    private List<Tag> tags;

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
}
