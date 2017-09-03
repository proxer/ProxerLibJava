package me.proxer.library.api.manga;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.manga.Chapter;
import me.proxer.library.entity.manga.Page;
import me.proxer.library.enums.Language;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class ChapterEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("chapter.json")));

        final Chapter result = api.manga()
                .chapter("15", 1, Language.ENGLISH)
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestChapter());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("chapter.json")));

        api.manga().chapter("4523", 1, Language.GERMAN)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/manga/chapter?id=4523&episode=1&language=de");
    }

    private Chapter buildTestChapter() {
        return new Chapter("1954", "2784", "Chapter 1", "0", "nobody",
                new Date(1318716000L * 1000), null, null, "0", Arrays.asList(
                new Page("fairy-001-01.jpg", 1100, 765),
                new Page("fairy-001-02-03.jpg", 641, 900)
        ));
    }
}
