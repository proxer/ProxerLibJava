package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ModifyUserInfoEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.info
                .note("123")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.info.note("321")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/setuserinfo"
    }

    @Test
    fun testNoteParameter() {
        val (_, request) = server.runRequest("empty.json") {
            api.info.note("321")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "id=321&type=note"
    }

    @Test
    fun testFavoriteParameter() {
        val (_, request) = server.runRequest("empty.json") {
            api.info.markAsFavorite("321")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "id=321&type=favor"
    }

    @Test
    fun testFinishedParameter() {
        val (_, request) = server.runRequest("empty.json") {
            api.info.markAsFinished("321")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "id=321&type=finish"
    }
}
