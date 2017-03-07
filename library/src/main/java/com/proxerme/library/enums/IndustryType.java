package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public enum IndustryType {
    @Json(name = "publisher")PUBLISHER,
    @Json(name = "studio")STUDIO,
    @Json(name = "producer")PRODUCER,
    @Json(name = "record_label")RECORD_LABEL,
    @Json(name = "talent_agent")TALENT_AGENT,
    @Json(name = "streaming")STREAMING
}
