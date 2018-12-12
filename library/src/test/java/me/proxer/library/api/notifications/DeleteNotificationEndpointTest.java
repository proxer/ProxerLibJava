package me.proxer.library.api.notifications;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class DeleteNotificationEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final Void result = api.notifications()
                .deleteNotification("123")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    void testPathDefault() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.notifications().deleteNotification("123")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/notifications/delete");
    }

    @Test
    void testParameterDefault() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.notifications().deleteNotification("123")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("nid=123");
    }

    @Test
    void testPathAll() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.notifications().deleteAllNotifications()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/notifications/delete");
    }

    @Test
    void testParameterAll() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.notifications().deleteAllNotifications()
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8()).isEqualTo("nid=0");
    }
}
