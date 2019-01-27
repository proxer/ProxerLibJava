package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AnimeLanguageTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(AnimeLanguage::class.java)

    @Test
    fun testDefault() {
        assertThat(adapter.fromJson("\"gersub\"")).isSameAs(AnimeLanguage.GERMAN_SUB)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(AnimeLanguage.OTHER)
    }
}
