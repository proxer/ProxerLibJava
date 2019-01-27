package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.IOException

class TagSubTypeTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(TagSubType::class.java)

    @Test
    fun testDefault() {
        assertThat(adapter.fromJson("\"story\"")).isSameAs(TagSubType.STORY)
    }

    @Test
    @Throws(IOException::class)
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(TagSubType.OTHER)
    }
}
