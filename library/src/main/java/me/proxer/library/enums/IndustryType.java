package me.proxer.library.enums;

import com.squareup.moshi.Json;

/**
 * Enum holding the available types.
 *
 * @author Ruben Gees
 */
public enum IndustryType {
    @Json(name = "publisher")PUBLISHER,
    @Json(name = "studio")STUDIO,
    @Json(name = "producer")PRODUCER,
    @Json(name = "record_label")RECORD_LABEL,
    @Json(name = "talent_agent")TALENT_AGENT,
    @Json(name = "streaming")STREAMING,
    @Json(name = "developer")DEVELOPER
}
