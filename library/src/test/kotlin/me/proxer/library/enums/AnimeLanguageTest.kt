package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class AnimeLanguageTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(AnimeLanguage::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"gersub\"") shouldBe AnimeLanguage.GERMAN_SUB
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe AnimeLanguage.OTHER
    }
}
