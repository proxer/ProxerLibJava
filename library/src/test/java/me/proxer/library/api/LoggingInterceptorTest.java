package me.proxer.library.api;

import me.proxer.library.BuildConfig;
import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerApi.Builder.LoggingStrategy;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees.
 */
public class LoggingInterceptorTest extends ProxerTest {

    private ByteArrayOutputStream loggerStream;
    private Handler loggerHandler;

    @Override
    @Before
    public void setUp() throws IOException {
        super.setUp();

        loggerStream = new ByteArrayOutputStream();
        loggerHandler = new StreamHandler(loggerStream, new EchoFormatter());

        Logger.getLogger("ProxerLibJava").addHandler(loggerHandler);
    }

    @Override
    @After
    public void tearDown() throws IOException {
        Logger.getLogger("ProxerLibJava").removeHandler(loggerHandler);

        super.tearDown();
    }

    @Test
    public void testLog() throws IOException, ProxerException {
        api = constructApi().loggingStrategy(LoggingStrategy.ALL).build();
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.notifications().news()
                .build()
                .execute();

        loggerHandler.flush();

        assertThat(loggerStream.toString()).isEqualTo("Requesting http://localhost:" + server.getPort()
                + "/api/v1/notifications/news with method GET and these headers:\n"
                + "proxer-api-key: mockKey\n"
                + "User-Agent: ProxerLibJava/" + BuildConfig.VERSION);
    }

    @Test
    public void testLogWithBody() throws IOException, ProxerException {
        api = constructApi().loggingStrategy(LoggingStrategy.ALL).build();
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));

        api.user().login("testerio", "pass")
                .build()
                .execute();

        loggerHandler.flush();

        assertThat(loggerStream.toString()).isEqualTo("Requesting http://localhost:" + server.getPort()
                + "/api/v1/user/login with method POST, these headers:\n"
                + "proxer-api-key: mockKey\n"
                + "User-Agent: ProxerLibJava/" + BuildConfig.VERSION + "\nand this body:\n"
                + "username=testerio&password=pass");
    }

    @Test
    public void testLogWithEmptyBody() throws IOException, ProxerException {
        api = constructApi().loggingStrategy(LoggingStrategy.ALL).build();
        server.enqueue(new MockResponse().setBody(fromResource("logout.json")));

        api.user().logout()
                .build()
                .execute();

        loggerHandler.flush();

        assertThat(loggerStream.toString()).isEqualTo("Requesting http://localhost:" + server.getPort()
                + "/api/v1/user/logout with method POST, these headers:\n"
                + "proxer-api-key: mockKey\n"
                + "User-Agent: ProxerLibJava/" + BuildConfig.VERSION + "\nand a blank body.");
    }

    @Test
    public void testLogAllOtherHost() throws IOException, ProxerException {
        api = constructApi().loggingStrategy(LoggingStrategy.ALL).build();
        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("http://example.com/test").build()).execute();

        loggerHandler.flush();

        assertThat(loggerStream.toString()).isEqualTo("Requesting http://localhost:" + server.getPort()
                + "/test with method GET and no headers.");
    }

    @Test
    public void testLogApiOnly() throws IOException, ProxerException {
        api = constructApi().loggingStrategy(LoggingStrategy.API).build();
        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("http://example.com/test").build()).execute();

        loggerHandler.flush();

        assertThat(loggerStream.toString()).isEmpty();
    }

    @Test
    public void testLogNone() throws IOException, ProxerException {
        api = constructApi().loggingStrategy(LoggingStrategy.NONE).build();
        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("http://example.com/test").build()).execute();

        loggerHandler.flush();

        assertThat(loggerStream.toString()).isEmpty();
    }

    private static class EchoFormatter extends Formatter {

        @Override
        public String format(final LogRecord record) {
            return record.getMessage();
        }
    }
}
