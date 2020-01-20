package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import me.proxer.library.entity.info.Entry
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.Gender
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DelimitedEnumSetAdapterFactoryTest {

    private val factory = DelimitedEnumSetAdapterFactory()

    private val moshi = Moshi.Builder()
        .add(factory)
        .build()

    private val annotation = GenderTestClass::class.java.getDeclaredField("genders")
        .getAnnotation(DelimitedEnumSet::class.java)

    @Test
    fun testCreate() {
        val type = Types.newParameterizedType(Set::class.java, Gender::class.java)
        val adapter = factory.create(type, setOf(annotation), moshi)

        adapter.shouldNotBeNull()
    }

    @Test
    fun testCreateInvalidType() {
        val type = Types.newParameterizedType(List::class.java, FskConstraint::class.java)
        val adapter = factory.create(type, setOf(annotation), moshi)

        adapter.shouldBeNull()
    }

    @Test
    fun testCreateInvalidParameterType() {
        val type = Types.newParameterizedType(Set::class.java, Entry::class.java)
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
        val type = Types.newParameterizedType(Set::class.java, Gender::class.java)
        val adapter = factory.create(type, emptySet(), moshi)

        adapter.shouldBeNull()
    }

    @Test
    fun testFromJsonSingle() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("""{"genders":"f"}""")

        result.shouldNotBeNull()
        result.genders shouldBeEqualTo setOf(Gender.FEMALE)
    }

    @Test
    fun testFromJsonMultiple() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("""{"genders":"f m"}""")

        result.shouldNotBeNull()
        result.genders shouldBeEqualTo setOf(Gender.MALE, Gender.FEMALE)
    }

    @Test
    fun testFromJsonInvalidDelimiter() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("""{"genders":"f,m"}""")

        result.shouldNotBeNull()
        result.genders shouldBeEqualTo setOf(Gender.UNKNOWN)
    }

    @Test
    fun testFromJsonEmpty() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("""{"genders":""}""")

        result.shouldNotBeNull()
        result.genders.shouldNotBeNull()
        result.genders.shouldBeEmpty()
    }

    @Test
    fun testFromJsonNull() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("""{"genders":null}""")

        result.shouldNotBeNull()
        result.genders.shouldNotBeNull()
        result.genders.shouldBeEmpty()
    }

    @Test
    fun testFromJsonDifferentDelimiter() {
        val result = moshi.adapter(GenderWithDelimiterTestClass::class.java).fromJson("""{"genders":"f, m"}""")

        result.shouldNotBeNull()
        result.genders shouldBeEqualTo setOf(Gender.MALE, Gender.FEMALE)
    }

    @Test
    fun testFromJsonFallback() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("""{"genders":"f m xyz"}""")

        result.shouldNotBeNull()
        result.genders shouldBeEqualTo setOf(Gender.MALE, Gender.FEMALE, Gender.UNKNOWN)
    }

    @Test
    fun testFromJsonNoFallback() {
        val adapter = moshi.adapter(FskConstrainTestClass::class.java)

        val result = invoking { adapter.fromJson("""{"fskConstraints":"fsk0 xyz"}""") } shouldThrow
            JsonDataException::class

        result.exceptionMessage shouldContain "Expected one of [fsk0"
        result.exceptionMessage shouldContain "but was xyz at path $.fskConstraints"
    }

    @Test
    fun testToJsonSingle() {
        val testSubject = GenderTestClass(setOf(Gender.FEMALE))
        val result = moshi.adapter(GenderTestClass::class.java).toJson(testSubject)

        result shouldBeEqualTo """{"genders":"f"}"""
    }

    @Test
    fun testToJsonMultiple() {
        val testSubject = GenderTestClass(setOf(Gender.FEMALE, Gender.MALE))
        val result = moshi.adapter(GenderTestClass::class.java).toJson(testSubject)

        result shouldBeEqualTo """{"genders":"f m"}"""
    }

    @Test
    fun testToJsonMultipleDifferentDelimiter() {
        val testSubject = GenderWithDelimiterTestClass(setOf(Gender.FEMALE, Gender.MALE))
        val result = moshi.adapter(GenderWithDelimiterTestClass::class.java).toJson(testSubject)

        result shouldBeEqualTo """{"genders":"f, m"}"""
    }

    @JsonClass(generateAdapter = true)
    data class GenderTestClass(
        @field:DelimitedEnumSet val genders: Set<Gender>? = null
    )

    @JsonClass(generateAdapter = true)
    data class GenderWithDelimiterTestClass(
        @field:DelimitedEnumSet(delimiter = ", ") val genders: Set<Gender>? = null
    )

    @JsonClass(generateAdapter = true)
    data class FskConstrainTestClass(
        @field:DelimitedEnumSet val fskConstraints: Set<FskConstraint>? = null
    )
}
