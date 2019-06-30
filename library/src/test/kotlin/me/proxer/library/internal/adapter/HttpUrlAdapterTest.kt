package me.proxer.library.internal.adapter

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class HttpUrlAdapterTest {

    private val adapter = HttpUrlAdapter()

    @Test
    fun testNormal() {
        adapter.fromJson("https://www.example.com") shouldEqual "https://www.example.com".toHttpUrlOrNull()
    }

    @Test
    fun testSchemaLess() {
        adapter.fromJson("//www.example.com") shouldEqual "http://www.example.com".toHttpUrlOrNull()
    }

    @Test
    fun testInvalid() {
        adapter.fromJson("example.com").shouldBeNull()
    }
}
