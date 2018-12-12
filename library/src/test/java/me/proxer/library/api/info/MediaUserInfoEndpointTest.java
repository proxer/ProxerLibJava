package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.info.MediaUserInfo;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class MediaUserInfoEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("media_user_info.json")));

        final MediaUserInfo result = api.info()
                .userInfo("123")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestUserInfo());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("media_user_info.json")));

        api.info().userInfo("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/userinfo?id=321");
    }

    private MediaUserInfo buildTestUserInfo() {
        return new MediaUserInfo(false, true, false, true);
    }
}
