package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.info.ForumDiscussion;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class ForumDiscussionsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("info_forum.json")));

        final List<ForumDiscussion> result = api.info()
                .forumDiscussions("1")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildFirstTestForumDiscussion());
        assertThat(result).last().isEqualTo(buildLastTestForumDiscussion());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("info_forum.json")));

        api.info().forumDiscussions("3")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/forum?id=3");
    }

    private ForumDiscussion buildFirstTestForumDiscussion() {
        return new ForumDiscussion("384098", "281", "Airing-Anime",
                "Overlord II - Diskussionsthread",
                15, 749, new Date(1514199320 * 1000L), "351626", "Asuka..",
                new Date(1517246199 * 1000L), "506979", "5devilz");
    }

    private ForumDiscussion buildLastTestForumDiscussion() {
        return new ForumDiscussion("381421", "56", "Anime- und Manga-News",
                "Overlord II – neues Visual, weitere Charaktere und Synchronsprecher enthüllt",
                35, 32334, new Date(1489228544 * 1000L), "19328", "Moeface",
                new Date(1514078674 * 1000L), "470614", "..Rhyanon.");
    }
}
