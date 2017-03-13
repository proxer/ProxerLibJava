package com.proxerme.library.api.user;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.user.TopTenEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class UserTopTenEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_top_ten_anime.json")));

        final List<TopTenEntry> result = api.user()
                .topTen(null, "rubygee")
                .category(Category.ANIME)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    public void testManga() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_top_ten_manga.json")));

        final List<TopTenEntry> result = api.user()
                .topTen(null, "rubygee")
                .category(Category.MANGA)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildMangaTestEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("user_top_ten_manga.json")));

        api.user().topTen("123", "rubygee")
                .category(Category.ANIME)
                .build()
                .execute();

        Assertions.assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/user/topten?uid=123&username=rubygee&kat=anime");
    }

    @Test
    public void testUserIdAndUsernameNull() {
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
