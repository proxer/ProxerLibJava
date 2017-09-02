package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Value;

/**
 * Container for the single ratings. This ranges from 1 to 5 and is 0 if the user has not rated a
 * specific (or all) section(s) yet.
 *
 * @author Desnoo
 */
@Value
public class RatingDetails {

    /**
     * Returns the genre rating.
     */
    @Json(name = "genre")
    private int genre;

    /**
     * Returns the story rating.
     */
    @Json(name = "story")
    private int story;

    /**
     * Returns the animation rating.
     */
    @Json(name = "animation")
    private int animation;

    /**
     * Returns the characters rating.
     */
    @Json(name = "characters")
    private int characters;

    /**
     * Returns the music rating.
     */
    @Json(name = "music")
    private int music;
}
