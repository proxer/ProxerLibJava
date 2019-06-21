package me.proxer.library.internal.adapter

import me.proxer.library.toProxerDate
import me.proxer.library.toProxerDateTime
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import java.text.ParseException
import java.util.Date

/**
 * @author Ruben Gees
 */
class DateAdapterTest {

    private val adapter = DateAdapter()

    @Test
    fun testFromJsonTimestamp() {
        adapter.fromJson("12345") shouldEqual Date((12345 * 1000).toLong())
    }

    @Test
    fun testFromJsonIso() {
        adapter.fromJson("2010-01-01 23:12:10") shouldEqual "2010-01-01 23:12:10".toProxerDateTime()
    }

    @Test
    fun testFromJsonIsoNoTime() {
        adapter.fromJson("2010-01-01") shouldEqual "2010-01-01".toProxerDate()
    }

    @Test
    fun testFromJsonIsoNoTimeEmpty() {
        adapter.fromJson("0000-00-00") shouldEqual "0000-00-00".toProxerDate()
    }

    @Test
    fun testFromJsonMalformed() {
        invoking { adapter.fromJson("malformed") } shouldThrow ParseException::class
    }

    @Test
    fun testToJson() {
        adapter.toJson(Date(123)) shouldEqual 123
    }
}
