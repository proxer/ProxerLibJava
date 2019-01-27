package me.proxer.library.entity.ucp

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.enums.UcpSettingConstraint
import me.proxer.library.internal.adapter.NumberBasedBoolean

/**
 * Entity representing the settings of the user.
 *
 * @property profileVisibility The visibility of the profile in general.
 * @property topTenVisibility The visibility of the top ten.
 * @property animeVisibility The visibility of the anime list.
 * @property mangaVisibility The visibility of the manga list.
 * @property commentVisibility The visibility of the comments.
 * @property forumVisibility The visibility of the forum entries.
 * @property friendVisibility The visibility of the friends.
 * @property friendRequestConstraint The constraint of who can request friendship.
 * @property aboutVisibility The visibility of the about page.
 * @property historyVisibility The visibility of the history.
 * @property guestBookVisibility The visibility of the guest book.
 * @property guestBookEntryConstraint The visibility of the gallery activities.
 * @property galleryVisibility The visibility of the gallery activities.
 * @property articleVisibility The visibility of the articles.
 * @property isHideTags If tags should be shown or not on media detail pages.
 * @property isShowAds If ads in general should be shown.
 * @property adInterval The interval in which (video-)ads should be shown.
 * This is a range from 0 to 10. The less the more often, except for 0, which means never.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class UcpSettings(
    @Json(name = "profil") val profileVisibility: UcpSettingConstraint,
    @Json(name = "profil_topten") val topTenVisibility: UcpSettingConstraint,
    @Json(name = "profil_anime") val animeVisibility: UcpSettingConstraint,
    @Json(name = "profil_manga") val mangaVisibility: UcpSettingConstraint,
    @Json(name = "profil_latestcomments") val commentVisibility: UcpSettingConstraint,
    @Json(name = "profil_forum") val forumVisibility: UcpSettingConstraint,
    @Json(name = "profil_connections") val friendVisibility: UcpSettingConstraint,
    @Json(name = "profil_connections_new") val friendRequestConstraint: UcpSettingConstraint,
    @Json(name = "profil_about") val aboutVisibility: UcpSettingConstraint,
    @Json(name = "profil_chronik") val historyVisibility: UcpSettingConstraint,
    @Json(name = "profil_board") val guestBookVisibility: UcpSettingConstraint,
    @Json(name = "profil_board_post") val guestBookEntryConstraint: UcpSettingConstraint,
    @Json(name = "profil_gallery") val galleryVisibility: UcpSettingConstraint,
    @Json(name = "profil_article") val articleVisibility: UcpSettingConstraint,
    @field:NumberBasedBoolean @Json(name = "hide_tags") val isHideTags: Boolean,
    @field:NumberBasedBoolean @Json(name = "ads_active") val isShowAds: Boolean,
    @Json(name = "ads_interval") val adInterval: Int
)
