package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Entity which holds info of the connection between an [Entry] and an [me.proxer.library.entity.user.User].
 *
 * @property isNoted If this media has been noted by the user.
 * @property isFinished If this media has been finished by the user.
 * @property isCanceled If this media has been canceled by the user.
 * @property isTopTen If this media is on the top ten list of the user.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class MediaUserInfo(
    @Json(name = "noted") val isNoted: Boolean,
    @Json(name = "finished") val isFinished: Boolean,
    @Json(name = "canceled") val isCanceled: Boolean,
    @Json(name = "topten") val isTopTen: Boolean
)
