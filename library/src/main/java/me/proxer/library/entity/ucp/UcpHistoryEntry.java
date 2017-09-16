package me.proxer.library.entity.ucp;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.Medium;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity representing a single entry in the history list.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class UcpHistoryEntry implements ProxerIdItem, ProxerDateItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "eid")
    private String id;

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

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "timestamp")
    private Date date;
}
