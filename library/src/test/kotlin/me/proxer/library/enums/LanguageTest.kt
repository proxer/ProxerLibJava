package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class LanguageTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(Language::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"de\"") shouldBe Language.GERMAN
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"XYZ\"") shouldBe Language.OTHER
    }
}
