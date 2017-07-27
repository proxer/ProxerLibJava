package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.proxer.library.enums.MediaLanguage;

import javax.annotation.Nonnull;

/**
 * Entity holding the data of a single anime chapter.
 *
 * @author Ruben Gees.
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class MangaEpisode extends Episode {

    @Json(name = "title")
    private final String title;

    public MangaEpisode(int number, MediaLanguage language, String title) {
        super(number, language);
        this.title = title;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }
}
