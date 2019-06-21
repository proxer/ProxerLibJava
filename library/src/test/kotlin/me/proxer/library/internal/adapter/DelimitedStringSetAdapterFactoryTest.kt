package me.proxer.library.internal.adapter

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
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

        adapter.shouldNotBeNull()
    }

    @Test
    fun testCreateInvalidType() {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter = factory.create(type, setOf(annotation), moshi)

        adapter.shouldBeNull()
    }

    @Test
    fun testCreateInvalidParameterType() {
        val type = Types.newParameterizedType(Set::class.java, Boolean::class.javaObjectType)
        val adapter = factory.create(type, setOf(annotation), moshi)

        adapter.shouldBeNull()
    }

    @Test
    fun testCreateNoParameterType() {
        val adapter = factory.create(Set::class.java, setOf(annotation), moshi)

        adapter.shouldBeNull()
    }

    @Test
    fun testCreateNoAnnotation() {
        val type = Types.newParameterizedType(Set::class.java, String::class.java)
        val adapter = factory.create(type, emptySet(), moshi)

        adapter.shouldBeNull()
    }

    @Test
    fun testFromJsonSingle() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("""{"value":"a"}""")

        result.shouldNotBeNull()
        result.value shouldEqual setOf("a")
    }

    @Test
    fun testFromJsonMultiple() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("""{"value":"a b"}""")

        result.shouldNotBeNull()
        result.value shouldEqual setOf("a", "b")
    }

    @Test
    fun testFromJsonMultipleSame() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("""{"value":"a b b"}""")

        result.shouldNotBeNull()
        result.value shouldEqual setOf("a", "b")
    }

    @Test
    fun testFromJsonEmpty() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("""{"value":""}""")

        result.shouldNotBeNull()
        result.value.shouldBeEmpty()
    }

    @Test
    fun testFromJsonNull() {
        val result = moshi.adapter(StringSetTestClass::class.java).fromJson("""{"value":null}""")

        result.shouldNotBeNull()
        result.value.shouldBeEmpty()
    }

    @Test
    fun testFromJsonDifferentDelimiter() {
        val result = moshi.adapter(StringSetWithDelimiterTestClass::class.java).fromJson("""{"value":"a, b,c"}""")

        result.shouldNotBeNull()
        result.value shouldEqual setOf("a", "b,c")
    }

    @Test
    fun testFromJsonValuesToKeep() {
        val result = moshi.adapter(StringSetWithValuesToKeepTestClass::class.java).fromJson("""{"value":"a b c"}""")

        result.shouldNotBeNull()
        result.value shouldEqual setOf("a b", "c")
    }

    @Test
    fun testToJsonSingle() {
        val subject = StringSetTestClass(setOf("a"))
        val result = moshi.adapter(StringSetTestClass::class.java).toJson(subject)

        result shouldEqual """{"value":"a"}"""
    }

    @Test
    fun testToJsonMultiple() {
        val subject = StringSetTestClass(setOf("a", "b", "c"))
        val result = moshi.adapter(StringSetTestClass::class.java).toJson(subject)

        result shouldEqual """{"value":"a b c"}"""
    }

    @Test
    fun testToJsonDifferentDelimiter() {
        val subject = StringSetWithDelimiterTestClass(setOf("a", "b", "c"))
        val result = moshi.adapter(StringSetWithDelimiterTestClass::class.java).toJson(subject)

        result shouldEqual """{"value":"a, b, c"}"""
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
