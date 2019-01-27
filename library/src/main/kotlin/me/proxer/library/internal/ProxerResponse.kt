package me.proxer.library.internal

import com.squareup.moshi.Json
import me.proxer.library.internal.adapter.NumberBasedBoolean

/**
 * @author Ruben Gees
 */
internal data class ProxerResponse<T>(
    @field:NumberBasedBoolean @Json(name = "error") val error: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "code") val code: Int,
    @Json(name = "data") val data: T?
) {

    val isSuccessful get() = !error
}
