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
public class SetSettingsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final Void result = api.ucp()
                .setSettings(buildTestSettings())
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setSettings(buildTestSettings())
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/setsettings");
    }

    @Test
    public void testParameters() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setSettings(buildTestSettings())
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("{\"ads_active\":1,\"ads_interval\":7,\"hide_tags\":0,\"profil\":3,\"profil_about\":0,"
                        + "\"profil_anime\":1,\"profil_article\":2,\"profil_board\":2,\"profil_board_post\":3,"
                        + "\"profil_chronik\":4,\"profil_connections\":2,\"profil_connections_new\":0,"
                        + "\"profil_forum\":3,\"profil_gallery\":1,\"profil_latestcomments\":3,\"profil_manga\":2,"
                        + "\"profil_topten\":2}");
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
