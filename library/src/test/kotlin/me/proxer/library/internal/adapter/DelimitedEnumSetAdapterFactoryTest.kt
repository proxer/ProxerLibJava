package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import me.proxer.library.entity.info.Entry
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.Gender
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.EnumSet

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

        assertThat(adapter).isNotNull
    }

    @Test
    fun testCreateInvalidType() {
        val type = Types.newParameterizedType(List::class.java, FskConstraint::class.java)
        val adapter = factory.create(type, setOf(annotation), moshi)

        assertThat(adapter).isNull()
    }

    @Test
    fun testCreateInvalidParameterType() {
        val type = Types.newParameterizedType(Set::class.java, Entry::class.java)
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
        val type = Types.newParameterizedType(Set::class.java, Gender::class.java)
        val adapter = factory.create(type, emptySet(), moshi)

        assertThat(adapter).isNull()
    }

    @Test
    fun testFromJsonSingle() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("{\"genders\":\"f\"}")

        assertThat(result).isNotNull
        assertThat(result?.genders).containsExactly(Gender.FEMALE)
    }

    @Test
    fun testFromJsonMultiple() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("{\"genders\":\"f m\"}")

        assertThat(result).isNotNull
        assertThat(result?.genders).containsExactly(Gender.MALE, Gender.FEMALE)
    }

    @Test
    fun testFromJsonInvalidDelimiter() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("{\"genders\":\"f,m\"}")

        assertThat(result).isNotNull
        assertThat(result?.genders).containsExactly(Gender.UNKNOWN)
    }

    @Test
    fun testFromJsonEmpty() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("{\"genders\":\"\"}")

        assertThat(result).isNotNull
        assertThat(result?.genders).isEmpty()
    }

    @Test
    fun testFromJsonNull() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("{\"genders\":null}")

        assertThat(result).isNotNull
        assertThat(result?.genders).isEmpty()
    }

    @Test
    fun testFromJsonDifferentDelimiter() {
        val result = moshi.adapter(GenderWithDelimiterTestClass::class.java).fromJson("{\"genders\":\"f, m\"}")

        assertThat(result).isNotNull
        assertThat(result?.genders).containsExactly(Gender.MALE, Gender.FEMALE)
    }

    @Test
    fun testFromJsonFallback() {
        val result = moshi.adapter(GenderTestClass::class.java).fromJson("{\"genders\":\"f m xyz\"}")

        assertThat(result).isNotNull
        assertThat(result?.genders).containsExactly(Gender.MALE, Gender.FEMALE, Gender.UNKNOWN)
    }

    @Test
    fun testFromJsonNoFallback() {
        val adapter = moshi.adapter(FskConstrainTestClass::class.java)

        assertThatExceptionOfType(JsonDataException::class.java)
            .isThrownBy { adapter.fromJson("{\"fskConstraints\":\"fsk0 xyz\"}") }
            .withMessageContaining("Expected one of [fsk0")
            .withMessageContaining("but was xyz at path $.fskConstraints")
    }

    @Test
    fun testToJsonSingle() {
        val testSubject =
            GenderTestClass(EnumSet.of(Gender.FEMALE))
        val result = moshi.adapter(GenderTestClass::class.java).toJson(testSubject)

        assertThat(result).isEqualTo("{\"genders\":\"f\"}")
    }

    @Test
    fun testToJsonMultiple() {
        val testSubject = GenderTestClass(
            EnumSet.of(
                Gender.FEMALE,
                Gender.MALE
            )
        )
        val result = moshi.adapter(GenderTestClass::class.java).toJson(testSubject)

        assertThat(result).isEqualTo("{\"genders\":\"m f\"}")
    }

    @Test
    fun testToJsonMultipleDifferentDelimiter() {
        val testSubject =
            GenderWithDelimiterTestClass(
                EnumSet.of(
                    Gender.FEMALE,
                    Gender.MALE
                )
            )
        val result = moshi.adapter(GenderWithDelimiterTestClass::class.java).toJson(testSubject)

        assertThat(result).isEqualTo("{\"genders\":\"m, f\"}")
    }

    private data class GenderTestClass(
        @field:DelimitedEnumSet val genders: Set<Gender>? = null
    )

    private data class GenderWithDelimiterTestClass(
        @field:DelimitedEnumSet(delimiter = ", ") val genders: Set<Gender>? = null
    )

    private data class FskConstrainTestClass(
        @field:DelimitedEnumSet val fskConstraints: Set<FskConstraint>? = null
    )
}
