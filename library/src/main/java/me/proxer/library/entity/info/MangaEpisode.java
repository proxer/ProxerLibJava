package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.proxer.library.enums.MediaLanguage;

import javax.annotation.Nullable;

/**
 * Entity holding the data of a single anime chapter.
 *
 * @author Ruben Gees
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onParam = @__({@Nullable}))
public final class MangaEpisode extends Episode {

    @Json(name = "title")
    private final String title;

    public MangaEpisode(final int number, final MediaLanguage language, final String title) {
        super(number, language);
        this.title = title;
    }

    @Override
    protected boolean canEqual(final Object other) {
        return other instanceof MangaEpisode;
    }
}
