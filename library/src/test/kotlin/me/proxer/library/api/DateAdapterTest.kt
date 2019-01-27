package me.proxer.library.api

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author Ruben Gees
 */
class DateAdapterTest {

    private val adapter = DateAdapter()

    @Test
    fun testFromJsonTimestamp() {
        assertThat(adapter.fromJson("12345")).isEqualTo(Date((12345 * 1000).toLong()))
    }

    @Test
    fun testFromJsonIso() {
        assertThat(adapter.fromJson("2010-01-01 23:12:10"))
            .isEqualTo(SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse("2010-01-01 23:12:10"))
    }

    @Test
    fun testFromJsonIsoNoTime() {
        assertThat(adapter.fromJson("2010-01-01"))
            .isEqualTo(SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY).parse("2010-01-01"))
    }

    @Test
    fun testFromJsonIsoNoTimeEmpty() {
        assertThat(adapter.fromJson("0000-00-00"))
            .isEqualTo(SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY).parse("0000-00-00"))
    }

    @Test
    fun testFromJsonMalformed() {
        assertThatExceptionOfType(ParseException::class.java).isThrownBy { adapter.fromJson("malformed") }
    }

    @Test
    fun testToJson() {
        assertThat(adapter.toJson(Date(123))).isEqualTo(123)
    }
}
