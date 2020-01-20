package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class LogoutEndpointTest : ProxerTest() {

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("logout.json") {
            api.user.logout()
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/user/logout"
    }
}
