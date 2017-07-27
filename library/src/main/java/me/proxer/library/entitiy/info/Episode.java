package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.*;
import lombok.experimental.FieldDefaults;
import me.proxer.library.enums.MediaLanguage;

import javax.annotation.Nonnull;

/**
 * Base entity holding the common data of an {@link AnimeEpisode} and {@link MangaEpisode}.
 *
 * @author Ruben Gees.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class Episode {

    /**
     * Returns the number of this episode.
     */
    @Json(name = "no")
    private int number;

    /**
     * Returns the language.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "typ")
    private MediaLanguage language;
}
