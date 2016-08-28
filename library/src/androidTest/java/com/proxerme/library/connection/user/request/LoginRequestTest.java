package com.proxerme.library.connection.user.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.user.entitiy.User;
import com.proxerme.library.test.R;
import com.proxerme.library.util.RequestTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;

/**
 * Tests for {@link LoginRequest}.
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class LoginRequestTest extends RequestTest {

    private static final String URL = "/api/v1/user/login";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "secretPassword";
    private static final String DELIMITER = "&";

    @Test
    public void testDefault() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.login)));

        User result = connection.executeSynchronized(new LoginRequest(USERNAME, PASSWORD)
                .withCustomHost(buildHostUrl(server.url(URL))));

        Assert.assertEquals(generateTestUser(), result);
    }

    @Test
    public void testPostParameters() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.login)));

        connection.executeSynchronized(new LoginRequest(USERNAME, PASSWORD)
                .withCustomHost(buildHostUrl(server.url(URL))));

        Assert.assertEquals(2, server.takeRequest().getBody().readUtf8().split(DELIMITER).length);
    }

    @Test
    public void testUsernameParameter() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.login)));

        connection.executeSynchronized(new LoginRequest(USERNAME, PASSWORD)
                .withCustomHost(buildHostUrl(server.url(URL))));

        Assert.assertEquals("username=" + USERNAME,
                server.takeRequest().getBody().readUtf8().split(DELIMITER)[0]);
    }

    @Test
    public void testPasswordParameter() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.login)));

        connection.executeSynchronized(new LoginRequest(USERNAME, PASSWORD)
                .withCustomHost(buildHostUrl(server.url(URL))));

        Assert.assertEquals("password=" + PASSWORD,
                server.takeRequest().getBody().readUtf8().split(DELIMITER)[1]);
    }

    private User generateTestUser() {
        return new User(USERNAME, PASSWORD, "121658", "121658_VHuZqz.jpg");
    }
}
