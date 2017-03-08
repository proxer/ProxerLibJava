package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.SynonymType;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding the synonyms of an entry name.
 *
 * @author Desnoo
 */
public class Synonym implements IdItem {

    @Json(name = "id")
    private String id;
    @Json(name = "eid")
    private String entryId;
    @Json(name = "type")
    private SynonymType type;
    @Json(name = "name")
    private String name;

    private Synonym() {

    }

    /**
     * The Constructor.
     *
     * @param id      The id.
     * @param entryId The id of the entry.
     * @param type    The type of the Synonym.
     * @param name    The name.
     */
    public Synonym(@NotNull final String id, @NotNull final String entryId, @NotNull final SynonymType type,
                   @NotNull final String name) {
        this.id = id;
        this.entryId = entryId;
        this.type = type;
        this.name = name;
    }

    /**
     * Returns the id of this name.
     *
     * @return The id.
     */
    @NotNull
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the id of the corresponding entry.
     *
     * @return The id of the corresponding entry.
     */
    @NotNull
    public String getEntryId() {
        return entryId;
    }

    /**
     * Returns the type of this entry.
     *
     * @return The type.
     */
    @NotNull
    public SynonymType getType() {
        return type;
    }

    /**
     * Returns the name of this entry.
     *
     * @return The name.
     */
    @NotNull
    public String getName() {
        return name;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Synonym synonym = (Synonym) o;

        if (!id.equals(synonym.id)) return false;
        if (!entryId.equals(synonym.entryId)) return false;
        if (type != synonym.type) return false;
        return name.equals(synonym.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + entryId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
