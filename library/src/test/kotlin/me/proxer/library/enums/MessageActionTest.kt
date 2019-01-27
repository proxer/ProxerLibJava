package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MessageActionTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(MessageAction::class.java)

    @Test
    fun testDefault() {
        assertThat(adapter.fromJson("\"addUser\"")).isSameAs(MessageAction.ADD_USER)
    }

    @Test
    fun testFallback() {
        assertThat(adapter.fromJson("\"xyz\"")).isSameAs(MessageAction.NONE)
    }
}
