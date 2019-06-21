package me.proxer.library.internal.adapter

import okhttp3.HttpUrl
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
        adapter.fromJson("https://www.example.com") shouldEqual HttpUrl.parse("https://www.example.com")
    }

    @Test
    fun testSchemaLess() {
        adapter.fromJson("//www.example.com") shouldEqual HttpUrl.parse("http://www.example.com")
    }

    @Test
    fun testInvalid() {
        adapter.fromJson("example.com").shouldBeNull()
    }
}
