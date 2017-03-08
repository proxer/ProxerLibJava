package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.*;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Entity holding the detail data of an Entry (Anime, Manga)
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class EntryCore implements IdItem {

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
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "fsk")
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
}
