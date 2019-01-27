package me.proxer.library.enums

import com.squareup.moshi.Json
import me.proxer.library.entity.info.Entry

/**
 * Enum holding the available stats of an [Entry].
 *
 * @author Ruben Gees
 */
enum class MediaState {

    @Json(name = "0")
    PRE_AIRING,

    @Json(name = "1")
    FINISHED,

    @Json(name = "2")
    AIRING,

    @Json(name = "3")
    CANCELLED,

    @Json(name = "4")
    CANCELLED_SUB
}
