package me.proxer.library.entity.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem

/**
 * Entity holding information about the user after a login.
 *
 * @property isTeamMember If the user is a team member.
 * @property isDonator If the user is currently a donator.
 * @property loginToken The login token for further authentication.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "uid") override val id: String,
    @Json(name = "avatar") override val image: String,
    @Json(name = "isTeam") val isTeamMember: Boolean,
    @Json(name = "isDonator") val isDonator: Boolean,
    @Json(name = "token") val loginToken: String
) : ProxerIdItem, ProxerImageItem
