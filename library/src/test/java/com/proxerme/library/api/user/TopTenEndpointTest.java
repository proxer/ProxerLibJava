package com.proxerme.library.api.user;

import com.proxerme.library.EndpointTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.user.TopTenEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class TopTenEndpointTest extends EndpointTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("topten_anime.json")));

        final List<TopTenEntry> result = api.user()
                .topTen(null, "rubygee")
                .category(Category.ANIME)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    public void testManga() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("topten_manga.json")));

        final List<TopTenEntry> result = api.user()
                .topTen(null, "rubygee")
                .category(Category.MANGA)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildMangaTestEntry());
    }

    private TopTenEntry buildTestEntry() {
        return new TopTenEntry("13633", "Boku dake ga Inai Machi", Category.ANIME, Medium.ANIMESERIES);
    }

    private TopTenEntry buildMangaTestEntry() {
        return new TopTenEntry("6015", "Citrus (Saburouta)", Category.MANGA, Medium.MANGASERIES);
    }
}
