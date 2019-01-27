package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

/**
 * Enum holding the available license types.
 *
 * @author Ruben Gees
 */
@FallbackEnum(name = "UNKNOWN")
enum class License {

    @Json(name = "0")
    UNKNOWN,

    @Json(name = "1")
    NOT_LICENSED,

    @Json(name = "2")
    LICENSED
}
