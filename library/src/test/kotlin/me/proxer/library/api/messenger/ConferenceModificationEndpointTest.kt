package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ConferenceModificationEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("conference_modification.json") {
            api.messenger
                .markConferenceAsRead("123")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPathRead() {
        val (_, request) = server.runRequest("conference_modification.json") {
            api.messenger.markConferenceAsRead("123")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/setread"
    }

    @Test
    fun testPathUnread() {
        val (_, request) = server.runRequest("conference_modification.json") {
            api.messenger.unmarkConferenceAsRead("123")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/setunread"
    }

    @Test
    fun testPathBlock() {
        val (_, request) = server.runRequest("conference_modification.json") {
            api.messenger.markConferenceAsBlocked("123")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/setblock"
    }

    @Test
    fun testPathUnblock() {
        val (_, request) = server.runRequest("conference_modification.json") {
            api.messenger.unmarkConferenceAsBlocked("123")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/setunblock"
    }

    @Test
    fun testPathFavour() {
        val (_, request) = server.runRequest("conference_modification.json") {
            api.messenger.markConferenceAsFavorite("123")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/setfavour"
    }

    @Test
    fun testPathUnfavour() {
        val (_, request) = server.runRequest("conference_modification.json") {
            api.messenger.unmarkConferenceAsFavorite("123")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/setunfavour"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("conference_modification.json") {
            api.messenger.markConferenceAsRead("321")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "conference_id=321"
    }
}
