package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.ucp.UcpTopTenEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class UcpTopTenEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("ucp_top_ten.json")));

        final List<UcpTopTenEntry> result = api.ucp()
                .topTen()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("ucp_top_ten.json")));

        api.ucp().topTen()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/topten");
    }

    private UcpTopTenEntry buildTestEntry() {
        return new UcpTopTenEntry("640467", "2357", "High School DxD", Medium.ANIMESERIES,
                Category.ANIME);
    }
}
