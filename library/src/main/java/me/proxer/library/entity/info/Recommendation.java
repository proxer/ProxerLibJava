package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.*;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Entity holding the data associated with a recommendation.
 *
 * @author Ruben Gees
 */
@Value
public class Recommendation implements ProxerIdItem {

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
    @Json(name = "genre")
    private Set<Genre> genres;

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
     * Returns the amount of positive votes.
     */
    @Json(name = "count_positive")
    private int positiveVotes;

    /**
     * Returns the amount of negative votes.
     */
    @Json(name = "count_negative")
    private int negativeVotes;

    /**
     * Returns the vote of the user if present when calling the respective api.
     */
    @Nullable
    @Json(name = "positive")
    private Boolean userVote;

    public Recommendation(final String id, final String name, final Set<Genre> genres,
                          final Set<FskConstraint> fskConstraints, final String description, final Medium medium,
                          final int episodeAmount, final MediaState state, final int ratingSum, final int ratingAmount,
                          final int clicks, final Category category, final License license, final int positiveVotes,
                          final int negativeVotes, @Nullable Boolean userVote) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.fskConstraints = fskConstraints;
        this.description = description;
        this.medium = medium;
        this.episodeAmount = episodeAmount;
        this.state = state;
        this.ratingSum = ratingSum;
        this.ratingAmount = ratingAmount;
        this.clicks = clicks;
        this.category = category;
        this.license = license;
        this.positiveVotes = positiveVotes;
        this.negativeVotes = negativeVotes;
        this.userVote = userVote;
    }

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
