package me.proxer.library.entitiy.list;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Genre;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Entity holding all relevant info about a single entry in the media list.
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class MediaListEntry implements ProxerIdItem {

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
     * Returns the selected medium.
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
     * Returns the languages, this entry is available in.
     */
    @Json(name = "language")
    private Set<MediaLanguage> languages;

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
