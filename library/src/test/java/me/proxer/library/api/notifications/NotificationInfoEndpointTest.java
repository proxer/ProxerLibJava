package me.proxer.library.api.notifications;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.notifications.NotificationInfo;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class NotificationInfoEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("notification_info.json")));

        final NotificationInfo result = api.notifications()
                .notificationInfo()
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestNotificationInfo());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("notification_info.json")));

        api.notifications()
                .notificationInfo()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/notifications/count");
    }

    private NotificationInfo buildTestNotificationInfo() {
        return new NotificationInfo(3, 0, 238, 93);
    }
}
