package me.proxer.library.entitiy.list;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.*;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Entity holding all relevant info about a single entry in an industry's project list.
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class IndustryProject implements ProxerIdItem {

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
     * Returns the selected genres.
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
     * Returns the selected medium.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the state.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "type")
    private IndustryType type;

    /**
     * Returns the state.
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
