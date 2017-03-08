package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.SynonymType;
import com.proxerme.library.interfaces.IdItem;
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
public class Synonym implements IdItem {

    /**
     * @return The id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * @return The id of the associated entry.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "eid")
    private String entryId;

    /**
     * @return The type.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "type")
    private SynonymType type;

    /**
     * @return The name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String name;
}
