package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class NotificationTypeTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final NotificationType notificationType = api.moshi().adapter(NotificationType.class).fromJson("\"ticket\"");

        assertThat(notificationType).isSameAs(NotificationType.TICKET);
    }

    @Test
    public void testFallback() throws IOException {
        final NotificationType notificationType = api.moshi().adapter(NotificationType.class).fromJson("\"xyz\"");

        assertThat(notificationType).isSameAs(NotificationType.OTHER);
    }
}
