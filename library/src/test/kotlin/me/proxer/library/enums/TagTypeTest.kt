package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TagTypeTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(TagType::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"entry_tag\"") shouldBe TagType.TAG
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe TagType.OTHER
    }
}
