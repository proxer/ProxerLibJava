package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Simple enum for the available types of devices, supported by Proxer.
 *
 * Each leads to a different theme and is cached with a cookie.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class Device {

    @Json(name = "")
    UNSPECIFIED,

    @Json(name = "default")
    DEFAULT,

    @Json(name = "mobile")
    MOBILE,

    @Json(name = "desktop")
    LEGACY_DESKTOP,

    @Json(name = "html")
    LEGACY_HTML
}
