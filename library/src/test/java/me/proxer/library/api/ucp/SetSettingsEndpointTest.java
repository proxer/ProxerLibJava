package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.ucp.UcpSettings;
import me.proxer.library.enums.UcpSettingConstraint;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static me.proxer.library.api.ProxerException.ServerErrorType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
class SetSettingsEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final List<String> result = api.ucp()
                .setSettings(buildTestSettings())
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setSettings(buildTestSettings())
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/setsettings");
    }

    @Test
    void testParameters() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setSettings(buildTestSettings())
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("profil=3&profil_topten=2&profil_anime=1&profil_manga=2&profil_latestcomments=3"
                        + "&profil_forum=3&profil_connections=2&profil_connections_new=0&profil_about=0"
                        + "&profil_chronik=4&profil_board=2&profil_board_post=3&profil_gallery=1&profil_article=2"
                        + "&hide_tags=0&ads_active=1&ads_interval=7");
    }

    @Test
    void testParametersSetters() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setSettings()
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
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("profil=3&profil_topten=2&profil_anime=1&profil_manga=2&profil_latestcomments=3"
                        + "&profil_forum=3&profil_connections=2&profil_connections_new=0&profil_about=0"
                        + "&profil_chronik=4&profil_board=2&profil_board_post=3&profil_gallery=1&profil_article=2"
                        + "&hide_tags=1&ads_active=0&ads_interval=1");
    }

    @Test
    void testParametersSettersPartial() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setSettings()
                .profileVisibility(UcpSettingConstraint.FRIENDS)
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("profil=1");
    }

    @Test
    void testParametersSettersNone() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setSettings()
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEmpty();
    }

    @Test
    void testError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("ucp_settings_error.json")));

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(() -> api.ucp()
                        .setSettings(buildTestSettings())
                        .build()
                        .execute())
                .matches(exception -> exception.getServerErrorType() == ServerErrorType.UCP_INVALID_SETTINGS);
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
