package me.proxer.library.api.forum

import me.proxer.library.ProxerTest
import me.proxer.library.entity.forum.Post
import me.proxer.library.entity.forum.Topic
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class TopicEndpointTest : ProxerTest() {

    private val expectedPosts = listOf(
        Post(
            id = "811201", parentId = "0", userId = "613171", username = "SilverCrow00", image = "613171_Kxk7cM.jpg",
            date = Date(1513552953L * 1000), signature = null, modifiedById = null, modifiedByName = null,
            modifiedDate = null, modifiedReason = null, message = "Wie oft oder wie lange guckt ihr so Anime?",
            thankYouAmount = 1
        ),
        Post(
            id = "811256", parentId = "811201", userId = "139597", username = "Miss-Otaku", image = "139597_cKc2zC.png",
            date = Date(1513626886L * 1000), signature = "", modifiedById = "123", modifiedByName = "test",
            modifiedDate = Date(15135529123L * 1000), modifiedReason = "just a test",
            message =
            """
                |Ich versuche, dass ganze im Rahmen zu halten. Meistens sind es so ungefähr 2-3 Folgen und über
                | den Tag verteilt. Leider werde ich oft schwach, gerade bei besonders spannenden Anime, dass ich auch
                | mehrere Folgen am Stück anschaue.
            """.trimMargin().replace(
                "\n",
                ""
            ),
            thankYouAmount = 0
        )
    )

    private val expectedTopic = Topic(
        categoryId = "19", categoryName = "Proxer Umfragen",
        subject = "Wie lange guckt ihr Anime durchschnittlich /Tag", isLocked = false, postAmount = 14, hits = 101,
        firstPostDate = Date(1513552953L * 1000), lastPostDate = Date(1514151527L * 1000), posts = expectedPosts
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("forum_topic.json") {
            api.forum
                .topic("1")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedTopic
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("forum_topic.json") {
            api.forum.topic("123")
                .page(0)
                .limit(10)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/forum/topic?id=123&p=0&limit=10"
    }
}
