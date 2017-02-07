package com.proxerme.library.util;

import com.proxerme.library.connection.ProxerConnection;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

/**
 * Base class for all {@link com.proxerme.library.connection.ProxerRequest} subclass tests.
 *
 * @author Ruben Gees
 */
public class RequestTest {

    protected MockWebServer server;
    protected ProxerConnection connection;

    protected RequestTest() {

    }

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        connection = new ProxerConnection.Builder("test").build();

        server.start();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }
}
