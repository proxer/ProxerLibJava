package me.proxer.library.api.users

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TeamDescriptionEndpointTest : ProxerTest() {

    private val expectedDescriptions = mapOf(
        "socialmedia" to "Der Social Media-Bereich ist für die Präsenz von Proxer zuständig.",
        "technik" to ""
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("team_descriptions.json") {
            api.users.teamDescriptions()
                .build()
                .safeExecute()
        }

        result shouldBeEqualTo expectedDescriptions
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("team_descriptions.json") {
            api.users.teamDescriptions()
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/users/teamdescriptions"
    }
}
