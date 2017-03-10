package com.proxerme.library.entitiy.list;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.enums.MediaState;
import com.proxerme.library.enums.Medium;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding all relevant info about a single entry in the media list (Anime, Manga)
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class MediaEntry implements IdItem {

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
    @Json(name = "genre")
    private String genres;

    /**
     * Returns the selected medium.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the amount of episodes.
     */
    @Json(name = "count")
    private int episodeCount;

    /**
     * Returns the state.
     */
    @Json(name = "state")
    private MediaState state;


    // ------- Members with a custom getter. ------- //

    @Json(name = "rate_sum")
    private int rateSum;

    @Json(name = "rate_count")
    private int rateCount;

    @Json(name = "language")
    private String languages;

    /**
     * Returns an array with the genres of this entry.
     */
    @SuppressWarnings("WrongConstant")
    @NotNull
    public String[] getGenres() {
        if (genres.isEmpty()) {
            return new String[0];
        } else {
            return genres.split(" ");
        }
    }

    /**
     * Returns the average rating.
     */
    public float getRating() {
        if (rateCount <= 0) {
            return -1;
        } else {
            return rateSum / rateCount;
        }
    }

    /**
     * Returns an array with the available languages.
     */
    @SuppressWarnings("WrongConstant")
    @NotNull
    public String[] getLanguages() {
        if (languages.isEmpty()) {
            return new String[0];
        } else {
            return languages.split(",");
        }
    }

}
