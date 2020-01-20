package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.MediaUserInfo
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class MediaUserInfoEndpointTest : ProxerTest() {

    private val expectedUserInfo = MediaUserInfo(
        isNoted = false, isFinished = true, isCanceled = false, isTopTen = true
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("media_user_info.json") {
            api.info
                .userInfo("123")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedUserInfo
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("media_user_info.json") {
            api.info.userInfo("321")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/userinfo?id=321"
    }
}
