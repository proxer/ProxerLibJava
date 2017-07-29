package me.proxer.library.api.info;

import com.squareup.moshi.Json;

/**
 * @author Ruben Gees
 */
enum UserInfoType {
    @Json(name = "note") NOTE,
    @Json(name = "favor") FAVORITE,
    @Json(name = "finish") FINISHED,
}
