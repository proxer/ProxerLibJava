package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
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
        assertThat(adapter.fromJson("\"single\"")).isSameAs(RelationshipStatus.SINGLE)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(RelationshipStatus.UNKNOWN)
    }
}
