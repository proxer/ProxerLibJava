package me.proxer.library.api.user;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.user.UserHistoryEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.Medium;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserHistoryEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("user_history.json")));

        final List<UserHistoryEntry> result = api.user().history("test", "supersecret")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildFirstTestEntry());
        assertThat(result).last().isEqualTo(buildLastTestEntry());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("user_history.json")));

        api.user().history("test", "supersecret")
                .page(1)
                .limit(12)
                .includeHentai(true)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/user/history?uid=test&username=supersecret"
                + "&p=1&limit=12&isH=true");
    }

    private UserHistoryEntry buildFirstTestEntry() {
        return new UserHistoryEntry("457484352", "16209", "Kono Subarashii Sekai ni Shukufuku wo! 2",
                MediaLanguage.ENGLISH_SUB, Medium.ANIMESERIES, Category.ANIME, 2);
    }

    private UserHistoryEntry buildLastTestEntry() {
        return new UserHistoryEntry("456582967", "3088", "Girls of the Wild's",
                MediaLanguage.ENGLISH, Medium.MANGASERIES, Category.MANGA, 182);
    }
}
