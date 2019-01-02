package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.api.DelimitedStringSet;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.License;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.Season;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Entity representing a single relation of an {@link Entry}.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Relation implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the genres.
     */
    @DelimitedStringSet(valuesToKeep = "Slice of Life")
    @Json(name = "genre")
    private Set<String> genres;

    /**
     * Returns the fsk ratings.
     */
    @Json(name = "fsk")
    private Set<FskConstraint> fskConstraints;

    /**
     * Returns the description.
     */
    @Json(name = "description")
    private String description;

    /**
     * Returns the medium
     */
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
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the type of license.
     */
    @Json(name = "license")
    private License license;

    /**
     * Returns the available languages.
     */
    @Json(name = "language")
    private Set<MediaLanguage> languages;

    /**
     * Returns the year this media was aired or published.
     */
    @Nullable
    @Getter
    @Json(name = "year")
    private Integer year;

    /**
     * Returns the available languages.
     */
    @Nullable
    @Getter
    @Json(name = "season")
    private Season season;

    /**
     * Returns the average of all ratings.
     */
    public final float getRating() {
        if (ratingAmount <= 0) {
            return 0;
        } else {
            return ratingSum / (float) ratingAmount;
        }
    }
}
