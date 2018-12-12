package me.proxer.library.api.anime;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class LinkEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("link.json")));

        final String result = api.anime()
                .link("17")
                .build()
                .execute();

        assertThat(result).isEqualTo("//www.dailymotion.com/embed/video/k4D1tfdhKG");
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("link.json")));

        api.anime().link("13")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/anime/link?id=13");
    }
}
