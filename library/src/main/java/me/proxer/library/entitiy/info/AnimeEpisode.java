package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.proxer.library.enums.MediaLanguage;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

/**
 * Entity holding the data of a single anime episode.
 *
 * @author Ruben Gees.
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class AnimeEpisode extends Episode {

    @Json(name = "types")
    private final Set<String> hosters;

    @Json(name = "typeimages")
    private final List<String> hosterImages;

    public AnimeEpisode(int number, MediaLanguage language, Set<String> hosters, List<String> hosterImages) {
        super(number, language);
        this.hosters = hosters;
        this.hosterImages = hosterImages;
    }

    @Nonnull
    public Set<String> getHosters() {
        return hosters;
    }

    @Nonnull
    public List<String> getHosterImages() {
        return hosterImages;
    }
}
