package me.proxer.library.api.user;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.user.UserInfo;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class UserInfoEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_info.json")));

        final UserInfo result = api.user()
                .info(null, "rubygee")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestInfo());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("user_info.json")));

        api.user().info("123", "rubygee")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/user/userinfo?uid=123&username=rubygee");
    }

    @Test
    public void testUserIdAndUsernameNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> api.user().info(null, null));
    }

    private UserInfo buildTestInfo() {
        return new UserInfo("121658", "RubyGee", "121658_cEBC8F.png", false, false,
                "Ihr könnt mich jederzeit anschreiben, Skype oder ProxerPn!", new Date(1485372334L * 1000),
                983, 38, 4201, 548, 8, 1700);
    }
}
