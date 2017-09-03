package me.proxer.library.api.list;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.list.IndustryCore;
import me.proxer.library.enums.Country;
import me.proxer.library.enums.IndustryType;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class IndustryListEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("industry_list.json")));

        final List<IndustryCore> result = api.list()
                .industryList()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestGroup());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("industry_list.json")));

        api.list().industryList()
                .country(Country.GERMANY)
                .search("test")
                .searchStart("test2")
                .page(3)
                .limit(17)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/industrys?start=test2&contains=test"
                + "&country=de&p=3&limit=17");
    }

    private IndustryCore buildTestGroup() {
        return new IndustryCore("493", "10Gauge", IndustryType.STUDIO, Country.JAPAN);
    }
}
