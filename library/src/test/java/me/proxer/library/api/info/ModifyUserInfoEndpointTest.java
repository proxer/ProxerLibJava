package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class ModifyUserInfoEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final Void result = api.info()
                .note("123")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.info().note("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/setuserinfo");
    }

    @Test
    void testNoteParameter() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.info().note("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("id=321&type=note");
    }

    @Test
    void testFavoriteParameter() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.info().markAsFavorite("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("id=321&type=favor");
    }

    @Test
    void testFinishedParameter() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.info().markAsFinished("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("id=321&type=finish");
    }
}
