package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SeasonTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(Season::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"1\"") shouldBe Season.WINTER
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe Season.UNSPECIFIED
    }
}
