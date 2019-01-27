package me.proxer.library.api

import okhttp3.HttpUrl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class HttpUrlAdapterTest {

    private val adapter = HttpUrlAdapter()

    @Test
    fun testNormal() {
        assertThat(adapter.fromJson("https://www.example.com")).isEqualTo(HttpUrl.parse("https://www.example.com"))
    }

    @Test
    fun testSchemaLess() {
        assertThat(adapter.fromJson("//www.example.com")).isEqualTo(HttpUrl.parse("http://www.example.com"))
    }

    @Test
    fun testInvalid() {
        assertThat(adapter.fromJson("example.com")).isNull()
    }
}
