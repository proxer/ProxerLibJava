package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.proxer.library.enums.MediaLanguage;

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

    public AnimeEpisode(final int number, final MediaLanguage language, final Set<String> hosters,
                        final List<String> hosterImages) {
        super(number, language);
        this.hosters = hosters;
        this.hosterImages = hosterImages;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof AnimeEpisode;
    }

    public Set<String> getHosters() {
        return hosters;
    }

    public List<String> getHosterImages() {
        return hosterImages;
    }
}
