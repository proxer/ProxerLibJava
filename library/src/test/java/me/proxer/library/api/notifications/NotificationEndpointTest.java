package me.proxer.library.api.notifications;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.notifications.Notification;
import me.proxer.library.enums.NotificationFilter;
import me.proxer.library.enums.NotificationType;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class NotificationEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("notification.json")));

        final List<Notification> result = api.notifications()
                .notifications()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestNotification());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("notification.json")));

        api.notifications().notifications()
                .page(0)
                .limit(10)
                .markAsRead(true)
                .filter(NotificationFilter.UNREAD)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/notifications/notifications?p=0&limit=10&set_read=true&filter=1");
    }

    private Notification buildTestNotification() {
        return new Notification("10185686", NotificationType.REMINDER, "49815105",
                HttpUrl.parse("https://proxer.me/chapter/2373/26/en#top"),
                "Lesezeichen: <u>Test123 Manga #26</u> ist online!",
                new Date(1494452692L * 1000L), "");
    }
}
