package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SynonymTypeTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(SynonymType::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"name\"") shouldBe SynonymType.ORIGINAL
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe SynonymType.OTHER
    }
}
