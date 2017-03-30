package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees.
 */
@Value
public class EpisodeInfo {

    /**
     * Returns the id.
     */
    @Json(name = "start")
    private int firstEpisode;

    /**
     * Returns the id.
     */
    @Json(name = "end")
    private int lastEpisode;

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "lang")
    private Set<MediaLanguage> availableLanguages;

    /**
     * Returns the id.
     */
    @Json(name = "state")
    private int userState;

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "episodes")
    private List<Episode> episodes;
}
