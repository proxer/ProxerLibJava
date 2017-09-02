package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;
import me.proxer.library.enums.MediaLanguage;

/**
 * Base entity holding the common data of an {@link AnimeEpisode} and {@link MangaEpisode}.
 *
 * @author Ruben Gees
 */
@Value
@NonFinal
@AllArgsConstructor()
public abstract class Episode {

    /**
     * Returns the number of this episode.
     */
    @Json(name = "no")
    private int number;

    /**
     * Returns the language.
     */
    @Json(name = "typ")
    private MediaLanguage language;
}
