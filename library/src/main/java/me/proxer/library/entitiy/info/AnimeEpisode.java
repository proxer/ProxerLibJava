package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.proxer.library.enums.MediaLanguage;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * TODO: Describe class
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

    @NotNull
    public Set<String> getHosters() {
        return hosters;
    }

    @NotNull
    public List<String> getHosterImages() {
        return hosterImages;
    }
}
