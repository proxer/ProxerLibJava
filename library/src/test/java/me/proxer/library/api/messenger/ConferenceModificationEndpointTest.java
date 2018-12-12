package me.proxer.library.api.messenger;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class ConferenceModificationEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_modification.json")));

        final Void result = api.messenger()
                .markConferenceAsRead("123")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    void testPathRead() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_modification.json")));

        api.messenger().markConferenceAsRead("123")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/messenger/setread");
    }

    @Test
    void testPathUnread() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_modification.json")));

        api.messenger().unmarkConferenceAsRead("123")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/messenger/setunread");
    }

    @Test
    void testPathBlock() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_modification.json")));

        api.messenger().markConferenceAsBlocked("123")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/messenger/setblock");
    }

    @Test
    void testPathUnblock() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_modification.json")));

        api.messenger().unmarkConferenceAsBlocked("123")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/messenger/setunblock");
    }

    @Test
    void testPathFavour() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_modification.json")));

        api.messenger().markConferenceAsFavorite("123")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/messenger/setfavour");
    }

    @Test
    void testPathUnfavour() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_modification.json")));

        api.messenger().unmarkConferenceAsFavorite("123")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/messenger/setunfavour");
    }

    @Test
    void testParameters() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_modification.json")));

        api.messenger().markConferenceAsRead("321")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("conference_id=321");
    }
}
