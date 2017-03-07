package com.proxerme.library.entitiy.info;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.TimeItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity representing a single tag.
 *
 * @author Ruben Gees
 */
public class Tag implements IdItem, TimeItem {

    @Json(name = "tid")
    private String id;
    @Json(name = "id")
    private String entryTagId;
    @Json(name = "timestamp")
    private Date time;
    @Json(name = "rate_flag")
    private boolean rated;
    @Json(name = "spoiler_flag")
    private boolean spoiler;
    @Json(name = "tag")
    private String name;
    @Json(name = "description")
    private String description;

    private Tag() {

    }

    /**
     * The constructor.
     *
     * @param id          The id of the tag.
     * @param entryTagId  The id of the entry tag.
     * @param time        The time this tag was associated.
     * @param rated       If this tag has been rated.
     * @param spoiler     If this tag is a spoiler.
     * @param name        The name.
     * @param description The description.
     */
    public Tag(@NotNull final String id, @NotNull final String entryTagId, @NotNull final Date time,
               final boolean rated, final boolean spoiler, @NotNull final String name,
               @NotNull final String description) {
        this.id = id;
        this.entryTagId = entryTagId;
        this.time = time;
        this.rated = rated;
        this.spoiler = spoiler;
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Returns the entry id.
     *
     * @return The entry id.
     */
    @NotNull
    public String getEntryTagId() {
        return entryTagId;
    }

    /**
     * Returns the time.
     *
     * @return The time.
     */
    @Override
    public Date getTime() {
        return time;
    }

    /**
     * Returns if this tag is rated.
     *
     * @return True, if it is rated.
     */
    public boolean isRated() {
        return rated;
    }

    /**
     * Returns if this tag is a spoiler.
     *
     * @return True, if it is a spoiler.
     */
    public boolean isSpoiler() {
        return spoiler;
    }

    /**
     * Returns the name.
     *
     * @return The name.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Returns the description.
     *
     * @return The description.
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Tag tag = (Tag) o;

        if (rated != tag.rated) return false;
        if (spoiler != tag.spoiler) return false;
        if (!id.equals(tag.id)) return false;
        if (!entryTagId.equals(tag.entryTagId)) return false;
        if (!time.equals(tag.time)) return false;
        if (!name.equals(tag.name)) return false;
        return description.equals(tag.description);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryTagId.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + (rated ? 1 : 0);
        result = 31 * result + (spoiler ? 1 : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
