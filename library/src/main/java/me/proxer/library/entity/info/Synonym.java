package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.SynonymType;

import javax.annotation.Nullable;

/**
 * Entity holding the synonyms of an entry name.
 *
 * @author Desnoo
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Synonym implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
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
