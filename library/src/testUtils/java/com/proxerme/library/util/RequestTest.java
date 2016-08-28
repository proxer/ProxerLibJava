package com.proxerme.library.util;

import android.support.test.InstrumentationRegistry;

import com.proxerme.library.connection.ProxerConnection;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

/**
 * Base class for all {@link com.proxerme.library.connection.ProxerRequest} subclass tests.
 *
 * @author Ruben Gees
 */
public class RequestTest {

    protected static MockWebServer server = new MockWebServer();
    protected static ProxerConnection connection = new ProxerConnection.Builder("test",
            InstrumentationRegistry.getContext()).build();

    @BeforeClass
    public static void setUpServer() throws IOException {
        server.start();
    }

    @AfterClass
    public static void tearDownServer() throws IOException {
        server.shutdown();
    }
}
