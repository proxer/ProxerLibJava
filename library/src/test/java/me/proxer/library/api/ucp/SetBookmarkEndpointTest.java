package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class SetBookmarkEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final Void result = api.ucp()
                .setBookmark("123", 12, MediaLanguage.ENGLISH_SUB, Category.ANIME)
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setBookmark("123", 12, MediaLanguage.ENGLISH_SUB, Category.ANIME)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/setreminder");
    }

    @Test
    public void testParameters() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.ucp().setBookmark("321", 7, MediaLanguage.GERMAN, Category.MANGA)
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("id=321&episode=7&language=de&kat=manga");
    }
}
