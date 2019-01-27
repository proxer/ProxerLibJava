package me.proxer.library.api.ucp

import me.proxer.library.ProxerException
import me.proxer.library.ProxerException.ServerErrorType
import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.UcpSettings
import me.proxer.library.enums.UcpSettingConstraint
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SetSettingsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        val result = api.ucp
            .setSettings(buildTestSettings())
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.ucp.setSettings(buildTestSettings())
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/ucp/setsettings")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.ucp.setSettings(buildTestSettings())
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo(
            "profil=3&profil_topten=2&profil_anime=1&profil_manga=2&profil_latestcomments=3"
                + "&profil_forum=3&profil_connections=2&profil_connections_new=0&profil_about=0"
                + "&profil_chronik=4&profil_board=2&profil_board_post=3&profil_gallery=1&profil_article=2"
                + "&hide_tags=0&ads_active=1&ads_interval=7"
        )
    }

    @Test
    fun testParametersSetters() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

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

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo(
            "profil=3&profil_topten=2&profil_anime=1&profil_manga=2&profil_latestcomments=3"
                + "&profil_forum=3&profil_connections=2&profil_connections_new=0&profil_about=0"
                + "&profil_chronik=4&profil_board=2&profil_board_post=3&profil_gallery=1&profil_article=2"
                + "&hide_tags=1&ads_active=0&ads_interval=1"
        )
    }

    @Test
    fun testParametersSettersPartial() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.ucp.setSettings()
            .profileVisibility(UcpSettingConstraint.FRIENDS)
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("profil=1")
    }

    @Test
    fun testParametersSettersNone() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.ucp.setSettings()
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEmpty()
    }

    @Test
    fun testError() {
        server.enqueue(MockResponse().setBody(fromResource("ucp_settings_error.json")))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy {
                api.ucp
                    .setSettings(buildTestSettings())
                    .build()
                    .execute()
            }
            .matches { exception -> exception.serverErrorType === ServerErrorType.UCP_INVALID_SETTINGS }
    }

    private fun buildTestSettings(): UcpSettings {
        return UcpSettings(
            UcpSettingConstraint.EVERYONE, UcpSettingConstraint.LOGGED_IN_USERS,
            UcpSettingConstraint.FRIENDS, UcpSettingConstraint.LOGGED_IN_USERS, UcpSettingConstraint.EVERYONE,
            UcpSettingConstraint.EVERYONE, UcpSettingConstraint.LOGGED_IN_USERS, UcpSettingConstraint.DEFAULT,
            UcpSettingConstraint.DEFAULT, UcpSettingConstraint.PRIVATE, UcpSettingConstraint.LOGGED_IN_USERS,
            UcpSettingConstraint.EVERYONE, UcpSettingConstraint.FRIENDS, UcpSettingConstraint.LOGGED_IN_USERS,
            false, true, 7
        )
    }
}
