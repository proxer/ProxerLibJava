package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.RatingDetails
import me.proxer.library.entity.user.UserComment
import me.proxer.library.enums.Category
import me.proxer.library.enums.CommentContentType
import me.proxer.library.enums.Medium
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class UserCommentsEndpointTest : ProxerTest() {

    private val expectedComment = UserComment(
        id = "1106146", entryId = "4704", entryName = "Sakurasou no Pet na Kanojo", medium = Medium.ANIMESERIES,
        category = Category.ANIME, authorId = "62", mediaProgress = UserMediaProgress.CANCELLED,
        ratingDetails = RatingDetails(0, 0, 0, 0, 0),
        content = "Der Anfang ok... aber dann... schrecklich...", overallRating = 2, episode = 9, helpfulVotes = 8,
        date = Date(1391476364L * 1000), author = "genesis", image = "62_L36C3N.png"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("user_comment.json") {
            api.user
                .comments("123", "abc")
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedComment
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("user_comment.json") {
            api.user.comments("123", "abc")
                .page(3)
                .limit(12)
                .minimumLength(1234)
                .category(Category.ANIME)
                .states(UserMediaProgress.WATCHED, UserMediaProgress.WATCHING)
                .hasContent(CommentContentType.COMMENT, CommentContentType.RATING)
                .build()
                .execute()
        }

        request.path shouldEqual """
            /api/v1/user/comments?uid=123&username=abc&kat=anime&p=3&limit=12&length=1234&state=0%2B1
            &has=comment%2Brating
        """.trimIndent().replace("\n", "")
    }

    @Test
    fun testIllegalArguments() {
        invoking { api.user.comments(null, null) } shouldThrow IllegalArgumentException::class
    }
}
