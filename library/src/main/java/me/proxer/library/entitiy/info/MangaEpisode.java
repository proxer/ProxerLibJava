package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.proxer.library.enums.MediaLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
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

    @NotNull
    public String getTitle() {
        return title;
    }
}
