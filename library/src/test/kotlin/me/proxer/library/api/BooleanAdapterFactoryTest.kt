package me.proxer.library.api

import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.HashSet

/**
 * @author Ruben Gees
 */
class BooleanAdapterFactoryTest {

    private val factory = BooleanAdapterFactory()
    private val moshi = Moshi.Builder().add(factory).build()

    private val numberBasedBooleanAnnotation = Dummy::class.java.getAnnotation(NumberBasedBoolean::class.java)

    private val primitiveBoolean = Boolean::class.javaPrimitiveType as Class<Boolean>
    private val boxedBoolean = Boolean::class.java

    @Test
    fun testCreatePrimitive() {
        val defaultBooleanAdapter = Moshi.Builder().build().adapter(primitiveBoolean)

        assertThat(factory.create(primitiveBoolean, emptySet(), moshi))
            .isNotNull
            .isInstanceOf(defaultBooleanAdapter.javaClass)
    }

    @Test
    fun testCreateBoxed() {
        val defaultBooleanAdapter = Moshi.Builder().build().adapter(boxedBoolean)

        assertThat(factory.create(Boolean::class.java, emptySet(), moshi))
            .isNotNull
            .isInstanceOf(defaultBooleanAdapter.javaClass)
    }

    @Test
    fun testCreatePrimitiveNumberBased() {
        val defaultBooleanAdapter = Moshi.Builder().build().adapter(primitiveBoolean)

        assertThat(factory.create(primitiveBoolean, setOf<Annotation>(numberBasedBooleanAnnotation), moshi))
            .isNotNull
            .isNotInstanceOf(defaultBooleanAdapter.javaClass)
    }

    @Test
    fun testCreateBoxedNumberBased() {
        val defaultBooleanAdapter = Moshi.Builder().build().adapter(boxedBoolean)

        assertThat(factory.create(boxedBoolean, setOf<Annotation>(numberBasedBooleanAnnotation), moshi))
            .isNotNull
            .isNotInstanceOf(defaultBooleanAdapter.javaClass)
    }

    @Test
    fun testCreateNonBoolean() {
        assertThat(factory.create(String::class.java, HashSet(), moshi)).isNull()
    }

    @NumberBasedBoolean
    private class Dummy
}
