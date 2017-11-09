package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.info.Recommendation;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Genre;
import me.proxer.library.enums.License;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecommendationsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("recommendations.json")));

        final List<Recommendation> result = api.info()
                .recommendations("17175")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestRecommendation());
    }

    @Test
    public void testManga() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("recommendations.json")));

        final List<Recommendation> result = api.info()
                .recommendations("17175")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestRecommendation());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("recommendations.json")));

        api.info().recommendations("17175")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/recommendations?id=17175");
    }

    private Recommendation buildTestRecommendation() {
        return new Recommendation("15023", "Boku no Hero Academia", EnumSet.of(Genre.ACTION),
                EnumSet.of(FskConstraint.FSK_12), "Izuku ist ein schwächlicher „Normalo“.", Medium.ANIMESERIES,
                13, MediaState.FINISHED, 29150, 3412, 58005, Category.ANIME,
                License.LICENSED, 65, 1, null);
    }
}
