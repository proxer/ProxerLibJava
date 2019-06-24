package me.proxer.library.api.comment

import me.proxer.library.ProxerTest
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CreateCommentEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.comment
                .create("12", UserMediaProgress.WILL_WATCH)
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .create("7", UserMediaProgress.CANCELLED)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/comments/create"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .create("3", UserMediaProgress.WATCHING)
                .rating(7)
                .episode(12)
                .comment("Test")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "eid=3&rating=7&episode=12&state=1&comment=Test"
    }

    @Test
    fun testParametersEmpty() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .create("19", UserMediaProgress.CANCELLED)
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "eid=19&rating=0&episode=0&state=3&comment="
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource("-1", "11")
    fun testRatingValidation(rating: Int) {
        invoking {
            api.comment.create("0", UserMediaProgress.CANCELLED).rating(rating)
        } shouldThrow IllegalArgumentException::class
    }
}
