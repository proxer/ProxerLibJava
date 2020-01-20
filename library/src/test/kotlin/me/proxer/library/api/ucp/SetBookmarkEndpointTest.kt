package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SetBookmarkEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.ucp
                .setBookmark("123", 12, MediaLanguage.ENGLISH_SUB, Category.ANIME)
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.setBookmark("123", 12, MediaLanguage.ENGLISH_SUB, Category.ANIME)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/ucp/setreminder"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.setBookmark("321", 7, MediaLanguage.GERMAN, Category.MANGA)
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "id=321&episode=7&language=de&kat=manga"
    }
}
