package me.proxer.library.entity.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.entity.ProxerImageItem
import java.util.Date

/**
 * Entity holding all basic info of a user.
 *
 * @property username The username.
 * @property isTeamMember If the user is a team member.
 * @property isDonator If the user is currently a donator.
 * @property status The current status message.
 * @property lastStatusChange The time of the last status change.
 * @property uploadPoints The upload points.
 * @property forumPoints The forum points.
 * @property animePoints The anime points.
 * @property mangaPoints The manga points.
 * @property infoPoints The info points.
 * @property miscPoints The miscellaneous points.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class UserInfo(
    @Json(name = "uid") override val id: String,
    @Json(name = "username") val username: String,
    @Json(name = "avatar") override val image: String,
    @Json(name = "isTeam") val isTeamMember: Boolean,
    @Json(name = "isDonator") val isDonator: Boolean,
    @Json(name = "status") val status: String,
    @Json(name = "status_time") val lastStatusChange: Date,
    @Json(name = "points_uploads") val uploadPoints: Int,
    @Json(name = "points_forum") val forumPoints: Int,
    @Json(name = "points_anime") val animePoints: Int,
    @Json(name = "points_manga") val mangaPoints: Int,
    @Json(name = "points_info") val infoPoints: Int,
    @Json(name = "points_misc") val miscPoints: Int
) : ProxerIdItem, ProxerImageItem
