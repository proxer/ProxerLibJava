package me.proxer.library.api.list;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entitiy.list.TranslatorGroupProject;
import me.proxer.library.enums.*;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class TranslatorGroupProjectListEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group_project_list.json")));

        final List<TranslatorGroupProject> result = api.list()
                .translatorGroupProjectList("111")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestProject());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group_project_list.json")));

        api.list().translatorGroupProjectList("321")
                .includeHentai(true)
                .projectState(ProjectState.LICENCED)
                .page(3)
                .limit(12)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/translatorgroupprojects?id=321&type=5" +
                "&isH=true&p=3&limit=12");
    }

    private TranslatorGroupProject buildTestProject() {
        return new TranslatorGroupProject("15775", "12-Sai: Chiccha na Mune no Tokimeki",
                EnumSet.of(Genre.COMEDY, Genre.ROMANCE), EnumSet.of(FskConstraint.FSK_0), Medium.ANIMESERIES,
                ProjectState.ONGOING, MediaState.FINISHED, 942, 136);
    }
}
