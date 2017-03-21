package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class DeleteFavoriteRequestTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final Void result = api.ucp()
                .deleteFavorite("123")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().deleteFavorite("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/deletefavorite");
    }

    @Test
    public void testParameter() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().deleteFavorite("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("id=321");
    }
}
