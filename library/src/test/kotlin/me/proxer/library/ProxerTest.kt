package me.proxer.library

import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.internal.TlsUtil
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.util.concurrent.TimeUnit

/**
 * Base class for all integration tests. It provides a ready to use [MockWebServer],
 * an OkHttpClient configured to change the host to that of the server
 * and a [ProxerApi] instance. After each test, the server is shutdown.
 *
 * @author Ruben Gees
 */
abstract class ProxerTest {

    private val tls: HandshakeCertificates = TlsUtil.localhost()

    protected val server = MockWebServer()

    protected val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(MockWebServerUrlRewriteInterceptor(server))
        .hostnameVerifier { _, _ -> true }
        .sslSocketFactory(tls.sslSocketFactory(), tls.trustManager)
        .connectTimeout(500, TimeUnit.MILLISECONDS)
        .writeTimeout(500, TimeUnit.MILLISECONDS)
        .readTimeout(500, TimeUnit.MILLISECONDS)
        .build()

    protected val api = ProxerApi.Builder("mock-key")
        .client(client)
        .build()

    @BeforeEach
    protected open fun setUp() {
        server.useHttps(tls.sslSocketFactory(), false)
        server.start()
    }

    @AfterEach
    protected fun tearDown() {
        server.shutdown()
    }
}
