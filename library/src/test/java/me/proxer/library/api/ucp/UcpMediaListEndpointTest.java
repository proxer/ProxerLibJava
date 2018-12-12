package me.proxer.library.api.ucp;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.user.UserMediaListEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.UserMediaListFilterType;
import me.proxer.library.enums.UserMediaListSortCriteria;
import me.proxer.library.enums.UserMediaProgress;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class UcpMediaListEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_media_list.json")));

        final List<UserMediaListEntry> result = api.ucp()
                .mediaList()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("user_media_list.json")));

        api.ucp().mediaList()
                .category(Category.ANIME)
                .page(0)
                .limit(5)
                .search("test")
                .searchStart("startTest")
                .filter(UserMediaListFilterType.WATCHING)
                .sort(UserMediaListSortCriteria.STATE_CHANGE_DATE_ASCENDING)
                .includeHentai(true)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/ucp/list?kat=anime&p=0&limit=5"
                + "&search=test&search_start=startTest&filter=stateFilter1&sort=stateChangeDateASC&isH=true");
    }

    private UserMediaListEntry buildTestEntry() {
        return new UserMediaListEntry("16257", "91 Days", 12, Medium.ANIMESERIES,
                MediaState.FINISHED, "18301850", "", UserMediaProgress.WATCHED, 12,
                0);
    }
}
