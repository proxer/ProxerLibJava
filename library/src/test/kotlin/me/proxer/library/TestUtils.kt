package me.proxer.library

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import java.lang.invoke.MethodHandles
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun fromResource(file: String): String = MethodHandles.lookup().lookupClass().classLoader
    .let { requireNotNull(it.getResourceAsStream(file)) }
    .let { it.source().buffer().readUtf8() }

fun String.toProxerDate(): Date = SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY).parse(this)
fun String.toProxerDateTime(): Date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse(this)

fun <T> MockWebServer.runRequest(response: MockResponse, action: () -> T): Pair<T, RecordedRequest> {
    enqueue(response)

    return try {
        action() to requireNotNull(takeRequest(3, TimeUnit.SECONDS))
    } catch (error: Throwable) {
        takeRequest(3, TimeUnit.SECONDS)

        throw error
    }
}

fun <T> MockWebServer.runRequestIgnoringError(response: MockResponse, action: () -> T): Pair<T?, RecordedRequest> {
    enqueue(response)

    return try {
        action() to requireNotNull(takeRequest(3, TimeUnit.SECONDS))
    } catch (error: Throwable) {
        null to requireNotNull(takeRequest(3, TimeUnit.SECONDS))
    }
}

fun <T> MockWebServer.runRequest(bodyFile: String, action: () -> T): Pair<T, RecordedRequest> {
    return runRequest(MockResponse().setBody(fromResource(bodyFile)), action)
}

fun <T> MockWebServer.runRequestIgnoringError(bodyFile: String, action: () -> T): Pair<T?, RecordedRequest> {
    return runRequestIgnoringError(MockResponse().setBody(fromResource(bodyFile)), action)
}
