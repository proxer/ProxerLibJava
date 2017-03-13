package com.proxerme.library.entitiy.ucp;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.entitiy.interfaces.TimeItem;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.MediaLanguage;
import com.proxerme.library.enums.Medium;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity representing a single entry in the history list.
 *
 * @author Ruben Gees
 */
@Value
public class UcpHistoryEntry implements IdItem, TimeItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "eid")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the language.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "language")
    private MediaLanguage language;

    /**
     * Returns the medium.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the category.
     */
    @Getter(onMethod = @__({@NotNull}))
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
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "timestamp")
    private Date time;
}
