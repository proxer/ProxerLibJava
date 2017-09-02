package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.SynonymType;

/**
 * Entity holding the synonyms of an entry name.
 *
 * @author Desnoo
 */
@Value
public class Synonym implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated entry.
     */
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the type.
     */
    @Json(name = "type")
    private SynonymType type;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;
}
