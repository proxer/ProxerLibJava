package me.proxer.library.api.user;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entitiy.user.UserMediaListEntry;
import me.proxer.library.enums.*;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class UserMediaListEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_media_list.json")));

        final List<UserMediaListEntry> result = api.user()
                .mediaList(null, "rubygee")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("user_media_list.json")));

        api.user().mediaList("1", "rubygee")
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

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/user/list?uid=1&username=rubygee" +
                "&kat=anime&p=0&limit=5&search=test&search_start=startTest" +
                "&filter=stateFilter1&sort=stateChangeDateASC&isH=true");
    }

    @Test
    public void testUserIdAndUsernameNull() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> api.user().mediaList(null, null));
    }

    private UserMediaListEntry buildTestEntry() {
        return new UserMediaListEntry("16257", "91 Days", 12, Medium.ANIMESERIES,
                MediaState.FINISHED, "18301850", "", UserMediaProgress.WATCHED, 12,
                0);
    }

}
