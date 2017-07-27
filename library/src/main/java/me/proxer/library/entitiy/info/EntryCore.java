package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.*;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Entity holding the detail data of an Entry (Anime, Manga). These are only the basics.
 * For full info, consult {@link Entry}.
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class EntryCore implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the genres.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "genre")
    private Set<Genre> genres;

    /**
     * Returns the fsk ratings.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "fsk")
    private Set<FskConstraint> fskConstraints;

    /**
     * Returns the description.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "description")
    private String description;

    /**
     * Returns the medium
     */
    @Getter(onMethod = @__({@Nonnull}))
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
    @Getter(onMethod = @__({@Nonnull}))
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
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the type of license.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "license")
    private License license;

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
