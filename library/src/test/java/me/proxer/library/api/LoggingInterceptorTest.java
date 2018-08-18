package me.proxer.library.api;

import me.proxer.library.BuildConfig;
import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerApi.Builder.LoggingStrategy;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import org.graalvm.compiler.core.common.SuppressFBWarnings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees.
 */
public class LoggingInterceptorTest extends ProxerTest {

    private ByteArrayOutputStream loggerStream;
    private Handler loggerHandler;
    private Logger logger;

    @SuppressFBWarnings(
            value = "LG_LOST_LOGGER_DUE_TO_WEAK_REFERENCE",
            justification = "False positive? Logger has a hard reference."
    )
    @Override
    @Before
    public void setUp() throws IOException, GeneralSecurityException {
        super.setUp();

        loggerStream = new ByteArrayOutputStream();
        loggerHandler = new StreamHandler(loggerStream, new EchoFormatter());
        logger = Logger.getLogger("ProxerLibJava");

        logger.addHandler(loggerHandler);
    }

    @Override
    @After
    public void tearDown() throws IOException {
        logger.removeHandler(loggerHandler);

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
        loggerStream.flush();

        assertThat(loggerStream.toString("UTF-8")).isEqualTo("Requesting https://"
                + server.getHostName() + ":" + server.getPort()
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
        loggerStream.flush();

        assertThat(loggerStream.toString("UTF-8")).isEqualTo("Requesting https://"
                + server.getHostName() + ":" + server.getPort()
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
        loggerStream.flush();

        assertThat(loggerStream.toString("UTF-8")).isEqualTo("Requesting https://"
                + server.getHostName() + ":" + server.getPort()
                + "/api/v1/user/logout with method POST, these headers:\n"
                + "proxer-api-key: mockKey\n"
                + "User-Agent: ProxerLibJava/" + BuildConfig.VERSION + "\nand a blank body.");
    }

    @Test
    public void testLogAllOtherHost() throws IOException {
        startHttpOnlyServer();

        api = constructApi().loggingStrategy(LoggingStrategy.ALL).build();

        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("http://example.com/test").build()).execute();

        loggerHandler.flush();
        loggerStream.flush();

        assertThat(loggerStream.toString("UTF-8")).isEqualTo("Requesting http://"
                + server.getHostName() + ":" + server.getPort()
                + "/test with method GET and no headers.");
    }

    @Test
    public void testLogApiOnly() throws IOException {
        startHttpOnlyServer();

        api = constructApi().loggingStrategy(LoggingStrategy.API).build();

        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("http://example.com/test").build()).execute();

        loggerHandler.flush();
        loggerStream.flush();

        assertThat(loggerStream.toString("UTF-8")).isEmpty();
    }

    @Test
    public void testLogNone() throws IOException {
        startHttpOnlyServer();

        api = constructApi().loggingStrategy(LoggingStrategy.NONE).build();

        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("http://example.com/test").build()).execute();

        loggerHandler.flush();
        loggerStream.flush();

        assertThat(loggerStream.toString("UTF-8")).isEmpty();
    }

    @Test
    public void testLogWithCustomLogger() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);
        final String expectedMessage = "Requesting https://"
                + server.getHostName() + ":" + server.getPort()
                + "/test with method GET and no headers.";

        final CustomLogger customLogger = message -> {
            if (message.equals(expectedMessage)) {
                lock.countDown();
            }

            // Failed: Not the message we want. The lock will never be counted down and timeout.
        };

        api = constructApi().customLogger(customLogger).loggingStrategy(LoggingStrategy.ALL).build();

        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("https://example.com/test").build()).execute();

        lock.await();
    }

    private static class EchoFormatter extends Formatter {

        @Override
        public String format(final LogRecord record) {
            return record.getMessage();
        }
    }
}
