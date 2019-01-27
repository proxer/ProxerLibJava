package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProjectStateTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(ProjectState::class.java)

    @Test
    fun testDefault() {
        assertThat(adapter.fromJson("\"1\"")).isSameAs(ProjectState.FINISHED)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(ProjectState.UNKNOWN)
    }
}
