package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class NumberBasedBooleanAdapterTest {

    private val adapter = Moshi.Builder()
        .add(BooleanAdapterFactory())
        .build()
        .adapter<Boolean>(Boolean::class.java, NumberBasedBoolean::class.java)

    @Test
    fun testFromBoolean() {
        val result = invoking { adapter.fromJson("true") } shouldThrow JsonDataException::class

        result.exceptionMessage shouldEqual "Expected a number, string or null but was BOOLEAN at path $"
    }

    @Test
    fun testFromBooleanFalse() {
        val result = invoking { adapter.fromJson("false") } shouldThrow JsonDataException::class

        result.exceptionMessage shouldEqual "Expected a number, string or null but was BOOLEAN at path $"
    }

    @Test
    fun testFromInt() {
        adapter.fromJson("1") shouldBe true
    }

    @Test
    fun testFromIntFalse() {
        adapter.fromJson("0") shouldBe false
    }

    @Test
    fun testFromIntInvalid() {
        val result = invoking { adapter.fromJson("3") } shouldThrow JsonDataException::class

        result.exceptionMessage shouldEqual "Expected a 1 or 0 but was 3 at path $"
    }

    @Test
    fun testFromString() {
        adapter.fromJson(""""true"""") shouldBe true
    }

    @Test
    fun testFromStringFalse() {
        adapter.fromJson(""""false"""") shouldBe false
    }

    @Test
    fun testFromStringInvalid() {
        val result = invoking { adapter.fromJson(""""invalid"""") } shouldThrow JsonDataException::class

        result.exceptionMessage shouldEqual "Expected a true or false but was invalid at path $"
    }

    @Test
    fun testFromStringInt() {
        adapter.fromJson(""""1"""") shouldBe true
    }

    @Test
    fun testFromStringIntFalse() {
        adapter.fromJson(""""0"""") shouldBe false
    }

    @Test
    fun testFromStringIntInvalid() {
        val result = invoking { adapter.fromJson(""""3"""") } shouldThrow JsonDataException::class

        result.exceptionMessage shouldEqual "Expected a 1 or 0 but was 3 at path $"
    }

    @Test
    fun testFromNull() {
        adapter.fromJson("null").shouldBeNull()
    }

    @Test
    fun testFromInvalid() {
        val result = invoking { adapter.fromJson("[]") } shouldThrow JsonDataException::class

        result.exceptionMessage shouldEqual "Expected a number, string or null but was BEGIN_ARRAY at path $"
    }

    @Test
    fun testToJson() {
        adapter.toJson(true) shouldEqual "1"
    }

    @Test
    fun testToJsonFalse() {
        adapter.toJson(false) shouldEqual "0"
    }

    @Test
    fun testToJsonNull() {
        adapter.toJson(null) shouldEqual "null"
    }
}
