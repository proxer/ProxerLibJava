package me.proxer.library.entitiy.ucp;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Entity representing a single entry in the history list.
 *
 * @author Ruben Gees
 */
@Value
public class Bookmark implements ProxerIdItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated {@link Entry}.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the category.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@Nonnull}))
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
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "language")
    private MediaLanguage language;

    /**
     * Returns the medium.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the state of the associated {@link Entry}.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "state")
    private MediaState state;

    /**
     * Returns the name of the chapter if the associated media is a manga and it is uploaded.
     */
    @Getter(onMethod = @__({@Nullable}))
    @Json(name = "chapterName")
    private String chapterName;

    /**
     * Returns if this episode is available yet.
     */
    @Json(name = "available")
    private boolean available;
}
