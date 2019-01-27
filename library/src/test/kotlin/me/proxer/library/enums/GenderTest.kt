package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.IOException

/**
 * @author Ruben Gees
 */
class GenderTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(Gender::class.java)

    @Test
    @Throws(IOException::class)
    fun testDefault() {
        assertThat(adapter.fromJson("\"f\"")).isSameAs(Gender.FEMALE)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(Gender.UNKNOWN)
    }
}
