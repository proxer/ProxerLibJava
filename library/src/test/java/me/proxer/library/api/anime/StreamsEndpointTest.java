package me.proxer.library.api.anime;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.anime.Stream;
import me.proxer.library.enums.AnimeLanguage;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class StreamsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("streams.json")));

        final List<Stream> result = api.anime()
                .streams("12", 1, AnimeLanguage.GERMAN_SUB)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestStream());
    }

    @Test
    public void testOfficial() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("streams.json")));

        final List<Stream> result = api.anime()
                .streams("3", 4, AnimeLanguage.GERMAN_SUB)
                .build()
                .execute();

        assertThat(result).element(2).isEqualTo(buildOfficialTestStream());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("streams.json")));

        api.anime().streams("33", 3, AnimeLanguage.GERMAN_DUB)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/anime/streams?id=33&episode=3&language=gerdub");
    }

    @Test
    public void testProxerStreamPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("streams.json")));

        api.anime().streams("35", 1, AnimeLanguage.ENGLISH_DUB)
                .includeProxerStreams(true)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/anime/proxerstreams?id=35&episode=1&language=engdub");
    }

    @Test
    public void testProxerStreamFalse() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("streams.json")));

        api.anime().streams("43", 3, AnimeLanguage.GERMAN_SUB)
                .includeProxerStreams(false)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/anime/streams?id=43&episode=3&language=gersub");
    }

    private Stream buildTestStream() {
        return new Stream("565484", "dailymotion", "Dailymotion", "dailymotion.png",
                "217857", "kollenbad", new Date(1454414911L * 1000), "698",
                "Grim-Subs", false);
    }

    private Stream buildOfficialTestStream() {
        return new Stream("565924", "novamov", "Auroravid/Novamov", "novamov.png",
                "66", "ProxerBot", new Date(1454767407L * 1000), "92",
                "Chinurarete-Subs", true);
    }
}
