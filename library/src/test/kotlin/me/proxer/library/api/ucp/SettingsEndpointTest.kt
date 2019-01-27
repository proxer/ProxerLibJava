package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.UcpSettings
import me.proxer.library.enums.UcpSettingConstraint
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SettingsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("ucp_settings.json")))

        val result = api.ucp
            .settings()
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestSettings())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("ucp_settings.json")))

        api.ucp.settings()
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/ucp/settings")
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
