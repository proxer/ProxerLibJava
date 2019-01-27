package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SynonymTypeTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(SynonymType::class.java)

    @Test
    fun testDefault() {
        assertThat(adapter.fromJson("\"name\"")).isSameAs(SynonymType.ORIGINAL)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(SynonymType.OTHER)
    }
}
