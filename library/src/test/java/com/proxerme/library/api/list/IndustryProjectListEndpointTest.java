package com.proxerme.library.api.list;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.list.IndustryProject;
import com.proxerme.library.enums.*;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class IndustryProjectListEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("industry_project_list.json")));

        final List<IndustryProject> result = api.list()
                .industryProjectList("333")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestProject());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("industry_project_list.json")));

        api.list().industryProjectList("543")
                .includeHentai(true)
                .type(IndustryType.RECORD_LABEL)
                .page(3)
                .limit(12)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/industryprojects?id=543&type=record_label" +
                "&isH=true&p=3&limit=12");
    }

    private IndustryProject buildTestProject() {
        return new IndustryProject("10198", "&Dropout", EnumSet.of(Genre.COMEDY, Genre.SCI_FI, Genre.YURI),
                EnumSet.noneOf(FskConstraint.class), Medium.ONESHOT, IndustryType.PUBLISHER, MediaState.FINISHED,
                109, 23);
    }
}
