package me.proxer.library.internal

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.internal.adapter.NumberBasedBoolean

/**
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
internal data class ProxerResponse<T>(
    @field:NumberBasedBoolean @Json(name = "error") internal val error: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "code") val code: Int?,
    @Json(name = "data") val data: T?
) {

    val isSuccessful = !error
}
