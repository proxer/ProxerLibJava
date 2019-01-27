package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.RatingDetails
import me.proxer.library.entity.user.UserComment
import me.proxer.library.enums.Category
import me.proxer.library.enums.Medium
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import java.util.Date

/**
 * @author Ruben Gees
 */
class UserCommentsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("user_comment.json")))

        val result = api.user
            .comments("123", "abc")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestComment())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("user_comment.json")))

        api.user.comments("123", "abc")
            .page(3)
            .limit(12)
            .minimumLength(1234)
            .category(Category.ANIME)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/user/comments?uid=123&username=abc&kat=anime&p=3&limit=12&length=1234")
    }

    @Test
    fun testIllegalArguments() {
        val internalApi = Retrofit.Builder()
            .baseUrl("http://example.com")
            .build()
            .create(InternalApi::class.java)

        assertThatThrownBy { UserCommentsEndpoint(internalApi, null, null) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    private fun buildTestComment(): UserComment {
        return UserComment(
            "1106146", "4704", "Sakurasou no Pet na Kanojo", Medium.ANIMESERIES,
            Category.ANIME, "62", UserMediaProgress.CANCELLED,
            RatingDetails(0, 0, 0, 0, 0),
            "Der Anfang ok... aber dann... schrecklich... Oh mein Gott der Anime ist meiner Meinung nach "
                + "unschaubar D:\nHabe es nach 10 Episoden abgebrochen... Habs einfach nicht mehr "
                + "durchgehalten -_- Diese Beziehungsscheiße ist schlimmer als bei jeder Horror Anime... "
                + "Sowas kann ich einfach nicht schauen. \n\n\nTut mir leid, I'm out.",
            2, 9, 8, Date(1391476364L * 1000), "genesis", "62_L36C3N.png"
        )
    }
}
