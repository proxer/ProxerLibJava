package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.info.AnimeEpisode;
import me.proxer.library.entity.info.EpisodeInfo;
import me.proxer.library.entity.info.MangaEpisode;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees.
 */
public class EpisodeInfoEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("episode_info_anime.json")));

        final EpisodeInfo result = api.info()
                .episodeInfo("1")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestInfo());
    }

    @Test
    public void testManga() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("episode_info_manga.json")));

        final EpisodeInfo result = api.info()
                .episodeInfo("12")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestInfoManga());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("episode_info_anime.json")));

        api.info().episodeInfo("17")
                .page(0)
                .limit(10)
                .includeNotAvailableEpisodes(true)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/info/listinfo?id=17&p=0&limit=10&includeNotAvailableChapters=true");
    }

    private EpisodeInfo buildTestInfo() {
        return new EpisodeInfo(1, 12, Category.ANIME,
                EnumSet.of(MediaLanguage.GERMAN_SUB, MediaLanguage.ENGLISH_SUB), 0, Arrays.asList(
                new AnimeEpisode(1, MediaLanguage.ENGLISH_SUB,
                        new HashSet<>(Arrays.asList("proxer-stream", "mp4upload", "videoweed",
                                "novamov", "streamcloud2")),
                        Arrays.asList("proxer-stream.png", "mp4upload.png", "videoweed.png",
                                "novamov.png", "streamcloud.png")),
                new AnimeEpisode(2, MediaLanguage.ENGLISH_SUB,
                        new HashSet<>(Arrays.asList("proxer-stream", "mp4upload", "videoweed",
                                "novamov", "streamcloud2")),
                        Arrays.asList("proxer-stream.png", "mp4upload.png", "videoweed.png",
                                "novamov.png", "streamcloud.png"))
        ));
    }

    private EpisodeInfo buildTestInfoManga() {
        return new EpisodeInfo(1, 50, Category.MANGA,
                EnumSet.of(MediaLanguage.ENGLISH), 12, Arrays.asList(
                new MangaEpisode(1, MediaLanguage.ENGLISH, "Chapter 1"),
                new MangaEpisode(2, MediaLanguage.ENGLISH, "Chapter 2")
        ));
    }
}
