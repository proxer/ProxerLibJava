package com.proxerme.library.entitiy.list;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.enums.*;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Entity holding all relevant info about a single entry in a translator group's project list.
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class TranslatorGroupProject implements ProxerIdItem {

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
     * Returns the selected genres.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "genre")
    private Set<Genre> genres;

    /**
     * Returns the fsk ratings.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "fsk")
    private Set<FskConstraint> fskConstraints;

    /**
     * Returns the selected medium.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the state.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "type")
    private ProjectState projectState;

    /**
     * Returns the state.
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
