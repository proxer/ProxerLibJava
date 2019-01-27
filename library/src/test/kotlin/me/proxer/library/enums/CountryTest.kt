package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import me.proxer.library.ProxerTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CountryTest : ProxerTest() {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(Country::class.java)

    @Test
    fun testDefault() {
        assertThat(adapter.fromJson("\"de\"")).isSameAs(Country.GERMANY)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(Country.OTHER)
    }
}
