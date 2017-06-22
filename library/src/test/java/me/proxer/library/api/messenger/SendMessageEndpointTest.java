package me.proxer.library.api.messenger;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.enums.MessageAction;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Ruben Gees
 */
public class SendMessageEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final String result = api.messenger()
                .sendMessage("123", "message")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.messenger().sendMessage("123", "message")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/messenger/setmessage");
    }

    @Test
    public void testParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.messenger().sendMessage("id", "someMessage")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("conference_id=id&text=someMessage");
    }

    @Test
    public void testActionParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.messenger().sendMessage("id", MessageAction.REMOVE_USER, "Testerio")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("conference_id=id&text=%2FremoveUser%20Testerio");
    }

    @Test
    public void testInvalidAction() throws ProxerException, IOException, InterruptedException {
        //noinspection CodeBlock2Expr
        assertThatThrownBy(() -> {
            api.messenger().sendMessage("id", MessageAction.NONE, "")
                    .build()
                    .execute();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
