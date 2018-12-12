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
class DeleteBookmarkEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final Void result = api.ucp()
                .deleteBookmark("123")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().deleteBookmark("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/deletereminder");
    }

    @Test
    void testParameter() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().deleteBookmark("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("id=321");
    }
}
