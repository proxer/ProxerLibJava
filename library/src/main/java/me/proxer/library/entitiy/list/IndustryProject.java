package me.proxer.library.entitiy.list;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.*;

import java.util.Set;

/**
 * Entity holding all relevant info about a single entry in an industry's project list.
 *
 * @author Desnoo
 */
@Value
public class IndustryProject implements ProxerIdItem {

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
     * Returns the selected genres.
     */
    @Json(name = "genre")
    private Set<Genre> genres;

    /**
     * Returns the fsk ratings.
     */
    @Json(name = "fsk")
    private Set<FskConstraint> fskConstraints;

    /**
     * Returns the selected medium.
     */
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the state.
     */
    @Json(name = "type")
    private IndustryType type;

    /**
     * Returns the state.
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
