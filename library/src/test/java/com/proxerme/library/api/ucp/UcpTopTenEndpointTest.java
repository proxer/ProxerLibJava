package com.proxerme.library.api.ucp;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.ucp.UcpTopTenEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class UcpTopTenEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException, ParseException {
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

    private UcpTopTenEntry buildTestEntry() throws ParseException {
        return new UcpTopTenEntry("640467", "2357", "High School DxD", Medium.ANIMESERIES,
                Category.ANIME);
    }
}
