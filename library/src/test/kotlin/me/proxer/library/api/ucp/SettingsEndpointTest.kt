package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.UcpSettings
import me.proxer.library.enums.UcpSettingConstraint
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SettingsEndpointTest : ProxerTest() {

    private val expectedSettings = UcpSettings(
        profileVisibility = UcpSettingConstraint.EVERYONE, topTenVisibility = UcpSettingConstraint.LOGGED_IN_USERS,
        animeVisibility = UcpSettingConstraint.FRIENDS, mangaVisibility = UcpSettingConstraint.LOGGED_IN_USERS,
        commentVisibility = UcpSettingConstraint.EVERYONE, forumVisibility = UcpSettingConstraint.EVERYONE,
        friendVisibility = UcpSettingConstraint.LOGGED_IN_USERS, friendRequestConstraint = UcpSettingConstraint.DEFAULT,
        aboutVisibility = UcpSettingConstraint.DEFAULT, historyVisibility = UcpSettingConstraint.PRIVATE,
        guestBookVisibility = UcpSettingConstraint.LOGGED_IN_USERS,
        guestBookEntryConstraint = UcpSettingConstraint.EVERYONE, galleryVisibility = UcpSettingConstraint.FRIENDS,
        articleVisibility = UcpSettingConstraint.LOGGED_IN_USERS, isHideTags = false, isShowAds = true, adInterval = 7
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("ucp_settings.json") {
            api.ucp
                .settings()
                .build()
                .execute()
        }

        result shouldEqual expectedSettings
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("ucp_settings.json") {
            api.ucp.settings()
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/ucp/settings"
    }
}
