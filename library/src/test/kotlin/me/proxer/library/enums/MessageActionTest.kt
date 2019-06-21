package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class MessageActionTest {

    private val adapter = Moshi.Builder()
        .add(FallbackEnum.ADAPTER_FACTORY)
        .build()
        .adapter(MessageAction::class.java)

    @Test
    fun testDefault() {
        adapter.fromJson("\"addUser\"") shouldBe MessageAction.ADD_USER
    }

    @Test
    fun testFallback() {
        adapter.fromJson("\"xyz\"") shouldBe MessageAction.NONE
    }
}
