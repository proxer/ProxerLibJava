package me.proxer.library.api.comment

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.Comment
import me.proxer.library.entity.info.RatingDetails
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class CommentEndpointTest : ProxerTest() {

    private val expectedComment = Comment(
        id = "7621372", entryId = "9723", authorId = "121658", mediaProgress = UserMediaProgress.WATCHED,
        ratingDetails = RatingDetails(genre = 5, story = 5, animation = 4, characters = 5, music = 5), content = "Test",
        overallRating = 10, episode = 24, helpfulVotes = 0, date = Date(1421530040L * 1000), author = "RubyGee",
        image = "121658_h29uKp.jpg"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("comment.json") {
            api.comment
                .comment(id = "1")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedComment
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("comment.json") {
            api.comment
                .comment(id = "12")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/comment/info?id=12"
    }

    @Test
    fun testPathEntry() {
        val (_, request) = server.runRequest("comment.json") {
            api.comment
                .comment(entryId = "7")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/comment/entry?eid=7"
    }

    @Test
    fun testIllegalArguments() {
        invoking { api.comment.comment(null, null) } shouldThrow IllegalArgumentException::class
    }
}
