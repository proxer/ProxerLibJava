package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entitiy.info.Industry;
import me.proxer.library.enums.Country;
import me.proxer.library.enums.IndustryType;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class IndustryEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("industry.json")));

        final Industry result = api.info()
                .industry("1")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestIndustry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("industry.json")));

        api.info().industry("3")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/industry?id=3");
    }

    private Industry buildTestIndustry() {
        return new Industry("123", "Hoods Entertainment", IndustryType.STUDIO, Country.JAPAN,
                HttpUrl.parse("http://www.hoods.co.jp/"),
                "Anfang 2009 gegründetes Animations-Studio mit Sitz in Tokio.");
    }
}
