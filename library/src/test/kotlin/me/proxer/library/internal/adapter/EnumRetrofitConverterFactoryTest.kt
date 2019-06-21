package me.proxer.library.internal.adapter

import me.proxer.library.entity.info.Entry
import me.proxer.library.enums.MediaLanguage
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import retrofit2.Retrofit

/**
 * @author Ruben Gees
 */
class EnumRetrofitConverterFactoryTest {

    private val factory = EnumRetrofitConverterFactory()
    private val retrofit = Retrofit.Builder().baseUrl("https://proxer.me").build()

    @Test
    fun testCreate() {
        factory.stringConverter(MediaLanguage::class.java, emptyArray(), retrofit).shouldNotBeNull()
    }

    @Test
    fun testCreateNoEnum() {
        factory.stringConverter(Entry::class.java, emptyArray(), retrofit).shouldBeNull()
    }

    @Test
    fun testConvert() {
        val converter = factory.stringConverter(MediaLanguage::class.java, emptyArray(), retrofit)

        converter.shouldNotBeNull()
        converter.convert(MediaLanguage.GERMAN) shouldEqual "de"
    }
}
