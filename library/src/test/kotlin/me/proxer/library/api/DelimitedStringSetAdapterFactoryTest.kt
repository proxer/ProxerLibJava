package me.proxer.library.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DelimitedStringSetAdapterFactoryTest {

    private val factory = DelimitedStringSetAdapterFactory()

    private val moshi = Moshi.Builder()
        .add(factory)
        .build()

    private val annotation = StringSetTestClass::class.java.getDeclaredField("value")
        .getAnnotation(DelimitedStringSet::class.java)

    @Test
    fun testCreate() {
        val type = Types.newParameterizedType(Set::class.java, String::class.java)
        val adapter = factory.create(type, setOf(annotation), moshi)

        assertThat(adapter).isNotNull
    }

    @Test
    fun testCreateInvalidType() {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = factory.create(type, setOf(annotation), moshi)

        assertThat(adapter).isNull()
    }

    @Test
    fun testCreateInvalidParameterType() {
        val type = Types.newParameterizedType(Set::class.java, Boolean::class.javaObjectType)
        val adapter = factory.create(type, setOf(annotation), moshi)

        assertThat(adapter).isNull()
    }

    @Test
    fun testCreateNoParameterType() {
        val adapter = factory.create(Set::class.java, setOf(annotation), moshi)

        assertThat(adapter).isNull()
    }

    @Test
    fun testCreateNoAnnotation() {
        val type = Types.newParameterizedType(Set::class.java, String::class.java)
        val adapter = factory.create(type, emptySet(), moshi)

        assertThat(adapter).isNull()
    }

    @Test
    fun testFromJsonSingle() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("{\"value\":\"a\"}")

        assertThat(result).isNotNull
        assertThat(result?.value).containsExactly("a")
    }

    @Test
    fun testFromJsonMultiple() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("{\"value\":\"a b\"}")

        assertThat(result).isNotNull
        assertThat(result?.value).containsExactly("a", "b")
    }

    @Test
    fun testFromJsonMultipleSame() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("{\"value\":\"a b b\"}")

        assertThat(result).isNotNull
        assertThat(result?.value).containsExactly("a", "b")
    }

    @Test
    fun testFromJsonEmpty() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("{\"value\":\"\"}")

        assertThat(result).isNotNull
        assertThat(result?.value).isEmpty()
    }

    @Test
    fun testFromJsonNull() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("{\"value\":null}")

        assertThat(result).isNotNull
        assertThat(result?.value).isEmpty()
    }

    @Test
    fun testFromJsonDifferentDelimiter() {
        val result = moshi.adapter(StringSetWithDelimiterTestClass::class.java).fromJson("{\"value\":\"a, b,c\"}")

        assertThat(result).isNotNull
        assertThat(result?.value).containsExactly("a", "b,c")
    }

    @Test
    fun testFromJsonValuesToKeep() {
        val result = moshi.adapter(StringSetWithValuesToKeepTestClass::class.java).fromJson("{\"value\":\"a b c\"}")

        assertThat(result).isNotNull
        assertThat(result?.value).containsExactlyInAnyOrder("a b", "c")
    }

    @Test
    fun testToJsonSingle() {
        val subject = StringSetTestClass(setOf("a"))
        val result = moshi.adapter(StringSetTestClass::class.java).toJson(subject)

        assertThat(result).isEqualTo("{\"value\":\"a\"}")
    }

    @Test
    fun testToJsonMultiple() {
        val subject = StringSetTestClass(setOf("a", "b", "c"))
        val result = moshi.adapter(StringSetTestClass::class.java).toJson(subject)

        assertThat(result).isEqualTo("{\"value\":\"a b c\"}")
    }

    @Test
    fun testToJsonDifferentDelimiter() {
        val subject = StringSetWithDelimiterTestClass(setOf("a", "b", "c"))
        val result = moshi.adapter(StringSetWithDelimiterTestClass::class.java).toJson(subject)

        assertThat(result).isEqualTo("{\"value\":\"a, b, c\"}")
    }

    private data class StringSetTestClass(
        @field:DelimitedStringSet val value: Set<String>
    )

    private data class StringSetWithDelimiterTestClass(
        @field:DelimitedStringSet(delimiter = ", ") val value: Set<String>
    )

    private data class StringSetWithValuesToKeepTestClass(
        @field:DelimitedStringSet(valuesToKeep = ["a b"]) val value: Set<String>
    )
}
