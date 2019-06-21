package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ProjectStateTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(ProjectState::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"1\"") shouldBe ProjectState.FINISHED
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe ProjectState.UNKNOWN
    }
}
