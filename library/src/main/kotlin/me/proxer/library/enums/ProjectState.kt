package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available states of a project.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "UNKNOWN")
enum class ProjectState {

    @Json(name = "0")
    UNKNOWN,

    @Json(name = "1")
    FINISHED,

    @Json(name = "2")
    ONGOING,

    @Json(name = "3")
    PLANNED,

    @Json(name = "4")
    CANCELLED,

    @Json(name = "5")
    LICENSED
}
