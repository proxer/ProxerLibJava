package com.proxerme.library.entitiy.info;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.enums.SynonymType;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding the synonyms of an entry name.
 *
 * @author Desnoo
 */
@SuppressWarnings("JavaDoc")
@Value
public class Synonym implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated entry.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the type.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "type")
    private SynonymType type;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String name;
}
