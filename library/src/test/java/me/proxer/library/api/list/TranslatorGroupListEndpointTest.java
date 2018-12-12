package me.proxer.library.api.list;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.list.TranslatorGroupCore;
import me.proxer.library.enums.Country;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class TranslatorGroupListEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group_list.json")));

        final List<TranslatorGroupCore> result = api.list()
                .translatorGroupList()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestGroup());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group_list.json")));

        api.list().translatorGroupList()
                .country(Country.JAPAN)
                .search("ab")
                .searchStart("cd")
                .page(1)
                .limit(100)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/translatorgroups?start=cd&contains=ab"
                + "&country=jp&p=1&limit=100");
    }

    private TranslatorGroupCore buildTestGroup() {
        return new TranslatorGroupCore("533", "/ak/ Scans", Country.ENGLAND,
                "http://img1.wikia.nocookie.net/__cb20120723230150/ak-scans/images/5/58/1342952821487.png");
    }
}
