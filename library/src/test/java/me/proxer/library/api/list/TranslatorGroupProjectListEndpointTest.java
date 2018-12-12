package me.proxer.library.api.list;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.list.TranslatorGroupProject;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Genre;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.ProjectState;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class TranslatorGroupProjectListEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group_project_list.json")));

        final List<TranslatorGroupProject> result = api.list()
                .translatorGroupProjectList("111")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestProject());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group_project_list.json")));

        api.list().translatorGroupProjectList("321")
                .includeHentai(true)
                .projectState(ProjectState.LICENSED)
                .page(3)
                .limit(12)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/translatorgroupprojects?id=321&type=5"
                + "&isH=0&p=3&limit=12");
    }

    @Test
    void testPathNoHentai() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group_project_list.json")));

        api.list().translatorGroupProjectList("321")
                .includeHentai(false)
                .projectState(ProjectState.LICENSED)
                .page(3)
                .limit(12)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/translatorgroupprojects?id=321&type=5"
                + "&isH=-1&p=3&limit=12");
    }

    @Test
    void testHentaiNull() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group_project_list.json")));

        api.list().translatorGroupProjectList("654")
                .includeHentai(true)
                .includeHentai(null)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/translatorgroupprojects?id=654");
    }

    private TranslatorGroupProject buildTestProject() {
        return new TranslatorGroupProject("15775", "12-Sai: Chiccha na Mune no Tokimeki",
                EnumSet.of(Genre.COMEDY, Genre.ROMANCE), EnumSet.of(FskConstraint.FSK_0), Medium.ANIMESERIES,
                ProjectState.ONGOING, MediaState.FINISHED, 942, 136);
    }
}
