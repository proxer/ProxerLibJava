package me.proxer.library.entity.ucp;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.api.NumberBasedBoolean;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;

import javax.annotation.Nullable;

/**
 * Entity representing a bookmark of the user.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Bookmark implements ProxerIdItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated {@link Entry}.
     */
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the category.
     */
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the episode.
     */
    @Json(name = "episode")
    private int episode;

    /**
     * Returns the language.
     */
    @Json(name = "language")
    private MediaLanguage language;

    /**
     * Returns the medium.
     */
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the state of the associated {@link Entry}.
     */
    @Json(name = "state")
    private MediaState state;

    /**
     * Returns the name of the chapter if the associated media is a manga and it is uploaded.
     */
    @Nullable
    @Getter
    @Json(name = "chapterName")
    private String chapterName;

    /**
     * Returns if this episode is available yet.
     */
    @NumberBasedBoolean
    @Json(name = "available")
    private boolean available;

    @SuppressWarnings("checkstyle:parameternumber")
    public Bookmark(final String id, final String entryId, final Category category, final String name,
                    final int episode, final MediaLanguage language, final Medium medium, final MediaState state,
                    @Nullable final String chapterName, final boolean available) {
        this.id = id;
        this.entryId = entryId;
        this.category = category;
        this.name = name;
        this.episode = episode;
        this.language = language;
        this.medium = medium;
        this.state = state;
        this.chapterName = chapterName;
        this.available = available;
    }
}
