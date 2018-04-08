package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageActionTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        MessageAction messageAction = api.moshi().adapter(MessageAction.class).fromJson("\"addUser\"");

        assertThat(messageAction).isSameAs(MessageAction.ADD_USER);
    }

    @Test
    public void testFallback() throws IOException {
        MessageAction messageAction = api.moshi().adapter(MessageAction.class).fromJson("\"xyz\"");

        assertThat(messageAction).isSameAs(MessageAction.NONE);
    }
}
