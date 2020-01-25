package me.proxer.library.api.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class UserInfoType {

    @Json(name = "note")
    NOTE,

    @Json(name = "favor")
    FAVORITE,

    @Json(name = "finish")
    FINISHED,

    @Json(name = "subscribe")
    SUBSCRIBE,

    @Json(name = "unsubscribe")
    UNSUBSCRIBE
}
