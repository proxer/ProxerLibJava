package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class WatchedEpisodesEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("watched_episodes.json")));

        final Integer result = api.ucp()
                .watchedEpisodes()
                .build()
                .execute();

        assertThat(result).isEqualTo(3243);
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("watched_episodes.json")));

        api.ucp().watchedEpisodes()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/listsum");
    }
}
