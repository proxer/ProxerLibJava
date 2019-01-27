package me.proxer.library.api

import com.squareup.moshi.JsonDataException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UnitAdapterTest {

    private val adapter = UnitAdapter()

    @Test
    fun testFromJsonNull() {
        assertThat(adapter.fromJson("null")).isNull()
    }

    @Test
    fun testFromJsonInvalid() {
        assertThatExceptionOfType(JsonDataException::class.java).isThrownBy { adapter.fromJson("value") }
    }

    @Test
    fun testToJson() {
        assertThat(adapter.toJson(null)).isEqualTo("null")
    }
}
