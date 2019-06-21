package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class RelationshipStatusTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(RelationshipStatus::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"single\"") shouldBe RelationshipStatus.SINGLE
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe RelationshipStatus.UNKNOWN
    }
}
