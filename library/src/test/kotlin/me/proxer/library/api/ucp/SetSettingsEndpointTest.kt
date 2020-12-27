package me.proxer.library.api.ucp

import me.proxer.library.ProxerException
import me.proxer.library.ProxerException.ServerErrorType
import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.UcpSettings
import me.proxer.library.enums.UcpSettingConstraint
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SetSettingsEndpointTest : ProxerTest() {

    private val settings = UcpSettings(
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
        val (result, _) = server.runRequest("empty.json") {
            api.ucp
                .setSettings(settings)
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.setSettings(settings)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/ucp/setsettings"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.setSettings(settings)
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo """
                profil=3&profil_topten=2&profil_anime=1&profil_manga=2&profil_latestcomments=3&profil_forum=3
                &profil_connections=2&profil_connections_new=0&profil_about=0&profil_chronik=4&profil_board=2
                &profil_board_post=3&profil_gallery=1&profil_article=2&hide_tags=0&ads_active=1&ads_interval=7
        """.trimIndent().replace("\n", "")
    }

    @Test
    fun testParametersSetters() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.setSettings()
                .profileVisibility(UcpSettingConstraint.EVERYONE)
                .topTenVisibility(UcpSettingConstraint.LOGGED_IN_USERS)
                .animeVisibility(UcpSettingConstraint.FRIENDS)
                .mangaVisibility(UcpSettingConstraint.LOGGED_IN_USERS)
                .commentVisibility(UcpSettingConstraint.EVERYONE)
                .forumVisibility(UcpSettingConstraint.EVERYONE)
                .friendVisibility(UcpSettingConstraint.LOGGED_IN_USERS)
                .friendRequestConstraint(UcpSettingConstraint.DEFAULT)
                .aboutVisibility(UcpSettingConstraint.DEFAULT)
                .historyVisibility(UcpSettingConstraint.PRIVATE)
                .guestBookVisibility(UcpSettingConstraint.LOGGED_IN_USERS)
                .guestBookEntryConstraint(UcpSettingConstraint.EVERYONE)
                .galleryVisibility(UcpSettingConstraint.FRIENDS)
                .articleVisibility(UcpSettingConstraint.LOGGED_IN_USERS)
                .hideTags(true)
                .showAds(false)
                .adInterval(1)
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo """
                profil=3&profil_topten=2&profil_anime=1&profil_manga=2&profil_latestcomments=3&profil_forum=3
                &profil_connections=2&profil_connections_new=0&profil_about=0&profil_chronik=4&profil_board=2
                &profil_board_post=3&profil_gallery=1&profil_article=2&hide_tags=1&ads_active=0&ads_interval=1
        """.trimIndent().replace("\n", "")
    }

    @Test
    fun testParametersSettersPartial() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.setSettings()
                .profileVisibility(UcpSettingConstraint.FRIENDS)
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "profil=1"
    }

    @Test
    fun testParametersSettersNone() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.setSettings()
                .build()
                .execute()
        }

        request.body.readUtf8().shouldBeEmpty()
    }

    @Test
    fun testError() {
        val result = invoking {
            server.runRequest("ucp_settings_error.json") {
                api.ucp
                    .setSettings(settings)
                    .build()
                    .execute()
            }
        } shouldThrow ProxerException::class

        result.exception.serverErrorType shouldBe ServerErrorType.UCP_INVALID_SETTINGS
    }
}
