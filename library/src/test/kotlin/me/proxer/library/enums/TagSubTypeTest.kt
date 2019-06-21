package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TagSubTypeTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(TagSubType::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"story\"") shouldBe TagSubType.STORY
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe TagSubType.OTHER
    }
}
