package com.proxerme.library.api.user;

import com.proxerme.library.ApiTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.user.ToptenEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class ToptenApiTest extends ApiTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("topten_anime.json")));

        final List<ToptenEntry> result = api.user()
                .topten(null, "rubygee")
                .category(Category.ANIME)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    private ToptenEntry buildTestEntry() {
        return new ToptenEntry("13633", "Boku dake ga Inai Machi", Category.ANIME, Medium.ANIMESERIES);
    }
}
