package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.UserAbout
import me.proxer.library.enums.Gender
import me.proxer.library.enums.RelationshipStatus
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UserAboutEndpointTest : ProxerTest() {

    private val expectedAbout = UserAbout(
        website = "", occupation = "Developer", interests = "Anime", city = "A City", country = "Some Country",
        about = "<p>Hello there", facebook = "", youtube = "", chatango = "", twitter = "", skype = "skypeTest",
        deviantart = "", birthday = "0000-06-02", gender = Gender.MALE, relationshipStatus = RelationshipStatus.UNKNOWN
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("user_about.json") {
            api.user
                .about(null, "rubygee")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedAbout
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("user_about.json") {
            api.user.about("123", "rubygee")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/user/about?uid=123&username=rubygee"
    }

    @Test
    fun testUserIdAndUsernameNull() {
        invoking { api.user.info(null, null) } shouldThrow IllegalArgumentException::class
    }
}
