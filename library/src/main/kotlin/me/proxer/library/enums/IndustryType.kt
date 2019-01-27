package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

/**
 * Enum holding the available types.
 *
 * @author Ruben Gees
 */
@FallbackEnum(name = "UNKNOWN")
enum class IndustryType {

    @Json(name = "publisher")
    PUBLISHER,

    @Json(name = "studio")
    STUDIO,

    @Json(name = "studio-secondary")
    STUDIO_SECONDARY,

    @Json(name = "producer")
    PRODUCER,

    @Json(name = "record_label")
    RECORD_LABEL,

    @Json(name = "talent_agent")
    TALENT_AGENT,

    @Json(name = "streaming")
    STREAMING,

    @Json(name = "developer")
    DEVELOPER,

    @Json(name = "tv")
    TV,

    @Json(name = "soundstudio")
    SOUND_STUDIO,

    @Json(name = "")
    UNKNOWN
}
