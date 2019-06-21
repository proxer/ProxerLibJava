package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonDataException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UnitAdapterTest {

    private val adapter = UnitAdapter()

    @Test
    fun testFromJsonNull() {
        adapter.fromJson("null").shouldBeNull()
    }

    @Test
    fun testFromJsonInvalid() {
        invoking { adapter.fromJson("value") } shouldThrow JsonDataException::class
    }

    @Test
    fun testToJson() {
        adapter.toJson(null) shouldEqual "null"
    }
}
