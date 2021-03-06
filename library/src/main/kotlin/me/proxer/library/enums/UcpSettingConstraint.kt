package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum holding the possible constraints for a setting in the UCP.
 *
 * @author Ruben Gees
 */
enum class UcpSettingConstraint {

    @Json(name = "0")
    DEFAULT,

    @Json(name = "1")
    FRIENDS,

    @Json(name = "2")
    LOGGED_IN_USERS,

    @Json(name = "3")
    EVERYONE,

    @Json(name = "4")
    PRIVATE
}
