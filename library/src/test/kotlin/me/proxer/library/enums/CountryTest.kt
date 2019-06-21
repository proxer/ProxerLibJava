package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import me.proxer.library.ProxerTest
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class CountryTest : ProxerTest() {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(Country::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"de\"") shouldBe Country.GERMANY
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe Country.OTHER
    }
}
