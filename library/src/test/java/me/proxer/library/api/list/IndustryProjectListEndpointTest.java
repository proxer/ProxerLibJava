package me.proxer.library.api.list;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.list.IndustryProject;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.IndustryType;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class IndustryProjectListEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("industry_project_list.json")));

        final List<IndustryProject> result = api.list()
                .industryProjectList("333")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestProject());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("industry_project_list.json")));

        api.list().industryProjectList("543")
                .includeHentai(true)
                .type(IndustryType.RECORD_LABEL)
                .page(3)
                .limit(12)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/industryprojects?id=543&type=record_label"
                + "&isH=0&p=3&limit=12");
    }

    @Test
    void testPathNoHentai() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("industry_project_list.json")));

        api.list().industryProjectList("543")
                .includeHentai(false)
                .type(IndustryType.RECORD_LABEL)
                .page(3)
                .limit(12)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/industryprojects?id=543&type=record_label"
                + "&isH=-1&p=3&limit=12");
    }

    @Test
    void testHentaiNull() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("industry_project_list.json")));

        api.list().industryProjectList("543")
                .includeHentai(true)
                .includeHentai(null)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/industryprojects?id=543");
    }

    private IndustryProject buildTestProject() {
        return new IndustryProject("10198", "&Dropout", new HashSet<>(Arrays.asList("Comedy", "SciFi", "Yuri")),
                EnumSet.noneOf(FskConstraint.class), Medium.ONESHOT, IndustryType.PUBLISHER, MediaState.FINISHED,
                109, 23);
    }
}
