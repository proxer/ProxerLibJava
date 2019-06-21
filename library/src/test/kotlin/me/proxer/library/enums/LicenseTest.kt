package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class LicenseTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(License::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"2\"") shouldBe License.LICENSED
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe License.UNKNOWN
    }
}
