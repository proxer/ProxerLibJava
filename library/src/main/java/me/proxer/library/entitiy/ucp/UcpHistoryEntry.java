package me.proxer.library.entitiy.ucp;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.Medium;

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
