package me.proxer.library.entity.list;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.api.DelimitedStringSet;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Entity holding all relevant info about a single entry in the media list.
 *
 * @author Desnoo
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class MediaListEntry implements ProxerIdItem {

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
    @DelimitedStringSet(valuesToKeep = "Slice of Life")
    @Json(name = "genre")
    private Set<String> genres;

    /**
     * Returns the selected medium.
     */
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
    public final float getRating() {
        if (ratingAmount <= 0) {
            return 0;
        } else {
            return ratingSum / (float) ratingAmount;
        }
    }
}
