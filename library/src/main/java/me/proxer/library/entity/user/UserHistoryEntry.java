package me.proxer.library.entity.user;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.Medium;

import javax.annotation.Nullable;

/**
 * Entity representing a single entry in the history list.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class UserHistoryEntry implements ProxerIdItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated {@link me.proxer.library.entity.info.Entry}.
     */
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

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
     * Returns the category.
     */
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the episode.
     */
    @Json(name = "episode")
    private int episode;
}
