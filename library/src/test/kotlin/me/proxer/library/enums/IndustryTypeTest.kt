package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IndustryTypeTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(IndustryType::class.java)

    @Test
    fun testDefault() {
        assertThat(adapter.fromJson("\"publisher\"")).isSameAs(IndustryType.PUBLISHER)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(IndustryType.UNKNOWN)
    }
}
