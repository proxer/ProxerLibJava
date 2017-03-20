package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entitiy.ucp.Bookmark;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class BookmarksEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("bookmarks.json")));

        final List<Bookmark> result = api.ucp()
                .bookmarks()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestBookmark());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("bookmarks.json")));

        api.ucp().bookmarks()
                .category(Category.MANGA)
                .page(12)
                .limit(1)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/reminder?kat=manga&p=12&limit=1");
    }

    private Bookmark buildTestBookmark() {
        return new Bookmark("46285981", "16656", Category.ANIME, "Kobayashi-san Chi no Maid Dragon",
                10, MediaLanguage.ENGLISH_SUB, Medium.ANIMESERIES, MediaState.AIRING, false);
    }
}
