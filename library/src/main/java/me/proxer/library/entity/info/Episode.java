package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import me.proxer.library.enums.MediaLanguage;

import javax.annotation.Nullable;

/**
 * Base entity holding the common data of an {@link AnimeEpisode} and {@link MangaEpisode}.
 *
 * @author Ruben Gees
 */
@Value
@NonFinal
@AllArgsConstructor()
@EqualsAndHashCode(onParam = @__({@Nullable}))
@SuppressWarnings({"pmd:AbstractClassWithoutAbstractMethod", "pmd:AbstractClassWithoutAnyMethod"})
public class Episode {

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
