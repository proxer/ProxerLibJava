package me.proxer.library.enums;

import com.serjltt.moshi.adapters.FallbackEnum;
import com.squareup.moshi.Json;

/**
 * Enum holding the available relationship statuses.
 *
 * @author Ruben Gees
 */
@FallbackEnum(name = "UNKNOWN")
public enum RelationshipStatus {
    @Json(name = "single") SINGLE,
    @Json(name = "in_relation") IN_RELATION,
    @Json(name = "engaged") ENGAGED,
    @Json(name = "complicated") COMPLICATED,
    @Json(name = "married") MARRIED,
    @Json(name = "searching") SEARCHING,
    @Json(name = "not-searching") NOT_SEARCHING,
    @Json(name = "") UNKNOWN,
}
