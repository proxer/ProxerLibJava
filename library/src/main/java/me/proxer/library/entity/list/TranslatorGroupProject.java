package me.proxer.library.entity.list;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.*;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Entity holding all relevant info about a single entry in a translator group's project list.
 *
 * @author Desnoo
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class TranslatorGroupProject implements ProxerIdItem {

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
    private ProjectState projectState;

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
