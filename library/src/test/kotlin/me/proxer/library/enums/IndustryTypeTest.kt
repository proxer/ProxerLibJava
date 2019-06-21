package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class IndustryTypeTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(IndustryType::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"publisher\"") shouldBe IndustryType.PUBLISHER
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe IndustryType.UNKNOWN
    }
}
