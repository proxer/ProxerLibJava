package com.proxerme.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Value;

/**
 * Container for the single ratings. This ranges from 1 to 5 and is 0 if the user has not rated a
 * specific (or all) section(s).
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class RatingDetails {

    /**
     * @return The genre rating.
     */
    @Json(name = "genre")
    private int genre;

    /**
     * @return The story rating.
     */
    @Json(name = "story")
    private int story;

    /**
     * @return The animation rating.
     */
    @Json(name = "animation")
    private int animation;

    /**
     * @return The characters rating.
     */
    @Json(name = "characters")
    private int characters;

    /**
     * @return The music rating.
     */
    @Json(name = "music")
    private int music;
}
