package me.proxer.library.api.user;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.user.UserAbout;
import me.proxer.library.enums.Gender;
import me.proxer.library.enums.RelationshipStatus;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class UserAboutEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_about.json")));

        final UserAbout result = api.user()
                .about(null, "rubygee")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestAbout());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("user_about.json")));

        api.user().about("123", "rubygee")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/user/about?uid=123&username=rubygee");
    }

    @Test
    public void testUserIdAndUsernameNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> api.user().info(null, null));
    }

    private UserAbout buildTestAbout() {
        return new UserAbout("", "Developer", "Anime", "A City", "Some Country",
                "<p>Hello there", "", "", "", "", "skypeTest",
                "", "0000-06-02", Gender.MALE, RelationshipStatus.UNKNOWN);
    }
}
