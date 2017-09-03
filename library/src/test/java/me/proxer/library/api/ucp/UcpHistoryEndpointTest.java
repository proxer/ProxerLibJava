package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.ucp.UcpHistoryEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class UcpHistoryEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException, ParseException {
        server.enqueue(new MockResponse().setBody(fromResource("ucp_history.json")));

        final List<UcpHistoryEntry> result = api.ucp()
                .history()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("ucp_history.json")));

        api.ucp().history()
                .page(0)
                .limit(10)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/history?p=0&limit=10");
    }

    private UcpHistoryEntry buildTestEntry() throws ParseException {
        return new UcpHistoryEntry("14701", "Nejimaki Seirei Senki: Tenkyou no Alderamin",
                MediaLanguage.ENGLISH_SUB, Medium.ANIMESERIES, Category.ANIME, 1,
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2017-03-13 23:16:36"));
    }
}
