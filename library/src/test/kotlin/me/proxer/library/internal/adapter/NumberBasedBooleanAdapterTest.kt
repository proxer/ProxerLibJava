package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class NumberBasedBooleanAdapterTest {

    private val adapter = Moshi.Builder().add(BooleanAdapterFactory()).build()
        .adapter<Boolean>(Boolean::class.java, NumberBasedBoolean::class.java)

    @Test
    fun testFromBoolean() {
        assertThatExceptionOfType(JsonDataException::class.java)
            .isThrownBy { adapter.fromJson("true") }
            .withMessage("Expected a number, string or null but was BOOLEAN at path $")
    }

    @Test
    fun testFromBooleanFalse() {
        assertThatExceptionOfType(JsonDataException::class.java)
            .isThrownBy { adapter.fromJson("false") }
            .withMessage("Expected a number, string or null but was BOOLEAN at path $")
    }

    @Test
    fun testFromInt() {
        assertThat(adapter.fromJson("1")).isTrue()
    }

    @Test
    fun testFromIntFalse() {
        assertThat(adapter.fromJson("0")).isFalse()
    }

    @Test
    fun testFromIntInvalid() {
        assertThatExceptionOfType(JsonDataException::class.java)
            .isThrownBy { adapter.fromJson("3") }
            .withMessage("Expected a 1 or 0 but was 3 at path $")
    }

    @Test
    fun testFromString() {
        assertThat(adapter.fromJson("\"true\"")).isTrue()
    }

    @Test
    fun testFromStringFalse() {
        assertThat(adapter.fromJson("\"false\"")).isFalse()
    }

    @Test
    fun testFromStringInvalid() {
        assertThatExceptionOfType(JsonDataException::class.java)
            .isThrownBy { adapter.fromJson("\"invalid\"") }
            .withMessage("Expected a true or false but was invalid at path $")
    }

    @Test
    fun testFromStringInt() {
        assertThat(adapter.fromJson("\"1\"")).isTrue()
    }

    @Test
    fun testFromStringIntFalse() {
        assertThat(adapter.fromJson("\"0\"")).isFalse()
    }

    @Test
    fun testFromStringIntInvalid() {
        assertThatExceptionOfType(JsonDataException::class.java)
            .isThrownBy { adapter.fromJson("\"3\"") }
            .withMessage("Expected a 1 or 0 but was 3 at path $")
    }

    @Test
    fun testFromNull() {
        assertThat(adapter.fromJson("null")).isNull()
    }

    @Test
    fun testFromInvalid() {
        assertThatExceptionOfType(JsonDataException::class.java)
            .isThrownBy { adapter.fromJson("[]") }
            .withMessage("Expected a number, string or null but was BEGIN_ARRAY at path $")
    }

    @Test
    fun testToJson() {
        assertThat(adapter.toJson(true)).isEqualTo("1")
    }

    @Test
    fun testToJsonFalse() {
        assertThat(adapter.toJson(false)).isEqualTo("0")
    }

    @Test
    fun testToJsonNull() {
        assertThat(adapter.toJson(null)).isEqualTo("null")
    }
}
