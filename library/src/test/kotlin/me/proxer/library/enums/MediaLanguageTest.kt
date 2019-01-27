package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MediaLanguageTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(MediaLanguage::class.java)

    @Test
    fun testDefault() {
        assertThat(adapter.fromJson("\"de\"")).isSameAs(MediaLanguage.GERMAN)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(MediaLanguage.OTHER)
    }
}
