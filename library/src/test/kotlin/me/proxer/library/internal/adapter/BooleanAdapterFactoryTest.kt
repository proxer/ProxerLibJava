package me.proxer.library.internal.adapter

import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeInstanceOf
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

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
        val createdAdapter = factory.create(primitiveBoolean, emptySet(), moshi)

        createdAdapter.shouldNotBeNull()
        createdAdapter shouldBeInstanceOf defaultBooleanAdapter.javaClass
    }

    @Test
    fun testCreateBoxed() {
        val defaultBooleanAdapter = Moshi.Builder().build().adapter(boxedBoolean)
        val createdAdapter = factory.create(Boolean::class.java, emptySet(), moshi)

        createdAdapter.shouldNotBeNull()
        createdAdapter shouldBeInstanceOf defaultBooleanAdapter.javaClass
    }

    @Test
    fun testCreatePrimitiveNumberBased() {
        val defaultBooleanAdapter = Moshi.Builder().build().adapter(primitiveBoolean)
        val createdAdapter = factory.create(primitiveBoolean, setOf(numberBasedBooleanAnnotation), moshi)

        createdAdapter.shouldNotBeNull()
        createdAdapter.shouldNotBeInstanceOf(defaultBooleanAdapter.javaClass)
    }

    @Test
    fun testCreateBoxedNumberBased() {
        val defaultBooleanAdapter = Moshi.Builder().build().adapter(boxedBoolean)
        val createdAdapter = factory.create(boxedBoolean, setOf(numberBasedBooleanAnnotation), moshi)

        createdAdapter.shouldNotBeNull()
        createdAdapter.shouldNotBeInstanceOf(defaultBooleanAdapter.javaClass)
    }

    @Test
    fun testCreateNonBoolean() {
        factory.create(String::class.java, emptySet(), moshi).shouldBeNull()
    }

    @NumberBasedBoolean
    private class Dummy
}
