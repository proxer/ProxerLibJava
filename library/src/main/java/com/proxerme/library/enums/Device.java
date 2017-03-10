package com.proxerme.library.enums;

import com.squareup.moshi.Json;

/**
 * Simple enum for the available types of devices, supported by Proxer.
 * <p>
 * Each leads to a different theme and is cached with a cookie.
 *
 * @author Ruben Gees
 */
public enum Device {
    @Json(name = "")UNSPECIFIED,
    @Json(name = "default")DEFAULT,
    @Json(name = "mobile")MOBILE
}
