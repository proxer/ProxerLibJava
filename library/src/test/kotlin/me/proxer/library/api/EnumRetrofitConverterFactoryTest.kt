package me.proxer.library.api

import me.proxer.library.entity.info.Entry
import me.proxer.library.enums.MediaLanguage
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
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
        assertThat(factory.stringConverter(MediaLanguage::class.java, emptyArray(), retrofit)).isNotNull
    }

    @Test
    fun testCreateNoEnum() {
        assertThat(factory.stringConverter(Entry::class.java, emptyArray(), retrofit)).isNull()
    }

    @Test
    fun testConvert() {
        val converter = factory.stringConverter(MediaLanguage::class.java, emptyArray(), retrofit)

        assertThat(converter?.convert(MediaLanguage.GERMAN)).isEqualTo("de")
    }
}
