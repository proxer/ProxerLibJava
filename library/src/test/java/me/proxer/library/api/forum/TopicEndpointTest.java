package me.proxer.library.api.forum;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.forum.Post;
import me.proxer.library.entity.forum.Topic;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class TopicEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("forum_topic.json")));

        final Topic result = api.forum()
                .topic("1")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestTopic());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("forum_topic.json")));

        api.forum().topic("123")
                .page(0)
                .limit(10)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/forum/topic?id=123&p=0&limit=10");
    }

    private Topic buildTestTopic() {
        final List<Post> posts = Arrays.asList(
                new Post("811201", "0", "613171", "SilverCrow00", "613171_Kxk7cM.jpg",
                        new Date(1513552953L * 1000), null, null, null,
                        null, null,
                        "Wie oft oder wie lange guckt ihr so Anime?", 1),
                new Post("811256", "811201", "139597", "Miss-Otaku", "139597_cKc2zC.png",
                        new Date(1513626886L * 1000), "",
                        "123", "test", new Date(15135529123L * 1000), "just a test",
                        "Ich versuche, dass ganze im Rahmen zu halten. Meistens sind es so ungefähr 2-3 "
                                + "Folgen und über den Tag verteilt. Leider werde ich oft schwach, gerade bei "
                                + "besonders spannenden Anime, dass ich auch mehrere Folgen am Stück anschaue.",
                        0)
        );

        return new Topic("19", "Proxer Umfragen",
                "Wie lange guckt ihr Anime durchschnittlich /Tag", false, 14, 101,
                new Date(1513552953L * 1000), new Date(1514151527L * 1000), posts);
    }
}
