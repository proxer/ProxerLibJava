package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class MessageActionTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final MessageAction messageAction = api.moshi().adapter(MessageAction.class).fromJson("\"addUser\"");

        assertThat(messageAction).isSameAs(MessageAction.ADD_USER);
    }

    @Test
    void testFallback() throws IOException {
        final MessageAction messageAction = api.moshi().adapter(MessageAction.class).fromJson("\"xyz\"");

        assertThat(messageAction).isSameAs(MessageAction.NONE);
    }
}
