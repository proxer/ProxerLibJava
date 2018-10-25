package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.ucp.UcpSettings;
import me.proxer.library.enums.UcpSettingConstraint;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class SettingsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("ucp_settings.json")));

        final UcpSettings result = api.ucp()
                .settings()
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestSettings());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("ucp_settings.json")));

        api.ucp().settings()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/settings");
    }

    private UcpSettings buildTestSettings() {
        return new UcpSettings(UcpSettingConstraint.EVERYONE, UcpSettingConstraint.LOGGED_IN_USERS,
                UcpSettingConstraint.FRIENDS, UcpSettingConstraint.LOGGED_IN_USERS, UcpSettingConstraint.EVERYONE,
                UcpSettingConstraint.EVERYONE, UcpSettingConstraint.LOGGED_IN_USERS, UcpSettingConstraint.DEFAULT,
                UcpSettingConstraint.DEFAULT, UcpSettingConstraint.PRIVATE, UcpSettingConstraint.LOGGED_IN_USERS,
                UcpSettingConstraint.EVERYONE, UcpSettingConstraint.FRIENDS, UcpSettingConstraint.LOGGED_IN_USERS,
                false, true, 7);
    }
}
