package me.proxer.library.api.messenger

import me.proxer.library.ProxerException
import me.proxer.library.ProxerTest
import me.proxer.library.entity.messenger.Conference
import me.proxer.library.enums.ConferenceType
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.IOException
import java.util.Date

/**
 * @author Ruben Gees
 */
class ConferencesEndpointTest : ProxerTest() {

    @Test
    @Throws(ProxerException::class, IOException::class)
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("conferences.json")))

        val result = api.messenger
            .conferences()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestConference())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("conferences.json")))

        api.messenger.conferences()
            .page(0)
            .type(ConferenceType.GROUP)
            .build()
            .execute()

        Assertions.assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/messenger/conferences?p=0&type=group")
    }

    private fun buildTestConference(): Conference {
        return Conference(
            "133663", "Novus4K", "", 2, "513596_5DoIo2.png",
            "avatar", false, true, Date(1488810726L * 1000), 0, "0"
        )
    }
}
