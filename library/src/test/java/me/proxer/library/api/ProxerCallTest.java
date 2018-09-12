package me.proxer.library.api;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException.ErrorType;
import me.proxer.library.api.ProxerException.ServerErrorType;
import me.proxer.library.entity.notifications.NewsArticle;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Ruben Gees
 */
public class ProxerCallTest extends ProxerTest {

    @Test
    public void testTimeoutError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json"))
                .setSocketPolicy(SocketPolicy.NO_RESPONSE));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.TIMEOUT,
                        "Exception should have the TIMEOUT ErrorType");
    }

    @Test
    public void testIOError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")).setResponseCode(404));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.IO,
                        "Exception should have the IO ErrorType");
    }

    @Test
    public void testInvalidEncodingError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json").replace(":", "invalid")));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.IO,
                        "Exception should have the IO ErrorType");
    }

    @Test
    public void testInvalidDataError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json").replace("256", "invalid")));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.PARSING,
                        "Exception should have the PARSING ErrorType");
    }

    @Test
    public void testServerError() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("conferences_error.json")));

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(() -> api.messenger().conferences().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.SERVER,
                        "Exception should have the SERVER ErrorType")
                .matches(exception -> exception.getServerErrorType() == ServerErrorType.MESSAGES_LOGIN_REQUIRED,
                        "Exception should have the MESSAGES_LOGIN_REQUIRED ServerErrorType")
                .matches(exception -> exception.getMessage() != null
                                && exception.getMessage().equals("Du bist nicht eingeloggt."),
                        "Exception should have the correct message");
    }

    @Test(timeout = 1000L)
    public void testEnqueue() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.notifications().news().build().enqueue(result -> lock.countDown(),
                exception -> {
                    // Failed. The lock will never be counted down and timeout.
                });

        lock.await();
    }

    @Test
    public void testEnqueueError() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")).setResponseCode(404));

        api.notifications().news().build().enqueue(result -> {
                    // Failed. The lock will never be counted down and timeout.
                },
                exception -> {
                    if (exception.getErrorType() == ErrorType.IO) {
                        lock.countDown();
                    }

                    // Failed: Not the exception we want. The lock will never be counted down and timeout.
                });

        lock.await();
    }

    @Test
    public void testIsExecuted() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.execute();

        assertThat(call.isExecuted()).isTrue();
    }

    @Test(timeout = 1000L)
    public void testCancel() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.enqueue(result -> {
            // Failed. The lock will never be counted down and timeout.
        }, exception -> {
            assertThat(exception.getErrorType()).isEqualTo(ErrorType.CANCELLED);

            lock.countDown();
        });

        call.cancel();
        lock.await();
    }

    @Test(timeout = 1000L)
    public void testIsCanceled() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.enqueue(result -> {
            // Failed. The lock will never be counted down and timeout.
        }, exception -> lock.countDown());

        call.cancel();
        lock.await();

        assertThat(call.isCanceled()).isTrue();
    }

    @Test
    public void testClone() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.execute();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        assertThat(call.clone().execute()).isNotNull();
    }

    @Test
    public void testRequest() {
        assertThat(api.notifications().news().build().request()).isNotNull();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testRequestThrowingErrorWithoutMessage() throws IOException {
        final Call<ProxerResponse<NewsArticle>> mockedCall = mock(Call.class);
        final ProxerCall<NewsArticle> call = new ProxerCall<>(mockedCall);

        when(mockedCall.execute()).thenThrow(new IOException());

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(call::execute)
                .withCauseExactlyInstanceOf(IOException.class)
                .matches(it -> it.getErrorType() == ErrorType.IO);
    }
}
