package me.proxer.library.entitiy.ucp;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.Medium;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * Entity representing a single entry in the history list.
 *
 * @author Ruben Gees
 */
@Value
public class UcpHistoryEntry implements ProxerIdItem, ProxerDateItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "eid")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "name")
    private String name;

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
     * Returns the category.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the episode.
     */
    @Json(name = "episode")
    private int episode;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "timestamp")
    private Date date;
}
