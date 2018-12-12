package me.proxer.library.api.user;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.user.TopTenEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class UserTopTenEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_top_ten_anime.json")));

        final List<TopTenEntry> result = api.user()
                .topTen(null, "rubygee")
                .category(Category.ANIME)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    void testManga() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_top_ten_manga.json")));

        final List<TopTenEntry> result = api.user()
                .topTen(null, "rubygee")
                .category(Category.MANGA)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildMangaTestEntry());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("user_top_ten_manga.json")));

        api.user().topTen("123", "rubygee")
                .category(Category.ANIME)
                .includeHentai(true)
                .build()
                .execute();

        Assertions.assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/user/topten?uid=123&username=rubygee&kat=anime&isH=true");
    }

    @Test
    void testUserIdAndUsernameNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> api.user().topTen(null, null));
    }

    private TopTenEntry buildTestEntry() {
        return new TopTenEntry("13633", "Boku dake ga Inai Machi", Category.ANIME, Medium.ANIMESERIES);
    }

    private TopTenEntry buildMangaTestEntry() {
        return new TopTenEntry("6015", "Citrus (Saburouta)", Category.MANGA, Medium.MANGASERIES);
    }
}
