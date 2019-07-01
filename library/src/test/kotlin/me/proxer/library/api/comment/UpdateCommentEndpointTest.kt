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

/**
 * @author Ruben Gees
 */
class UpdateCommentEndpointTest : ProxerTest() {

    @Test
    fun testDefaultCreate() {
        val (result, _) = server.runRequest("empty.json") {
            api.comment
                .create("8")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPathCreate() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .create("33")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/comments/create"
    }

    @Test
    fun testParametersCreate() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .create("18")
                .rating(2)
                .episode(0)
                .progress(UserMediaProgress.WILL_WATCH)
                .comment("Test Create")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "eid=18&rating=2&episode=0&state=2&comment=Test%20Create"
    }

    @Test
    fun testParametersEmptyCreate() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .create("4")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "eid=4"
    }

    @Test
    fun testDefaultUpdate() {
        val (result, _) = server.runRequest("empty.json") {
            api.comment
                .update("2")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPathUpdate() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .update("9")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/comments/update"
    }

    @Test
    fun testParametersUpdate() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .update("77")
                .rating(4)
                .episode(19)
                .progress(UserMediaProgress.CANCELLED)
                .comment("Test Update")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "id=77&rating=4&episode=19&state=3&comment=Test%20Update"
    }

    @Test
    fun testParametersEmptyUpdate() {
        val (_, request) = server.runRequest("empty.json") {
            api.comment
                .update("19")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "id=19"
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource("-1", "11")
    fun testRatingValidation(rating: Int) {
        invoking {
            api.comment.update("0").rating(rating)
        } shouldThrow IllegalArgumentException::class
    }
}
