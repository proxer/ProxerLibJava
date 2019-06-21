package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.ForumDiscussion
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ForumDiscussionsEndpointTest : ProxerTest() {

    private val expectedFirstForumDiscussion = ForumDiscussion(
        id = "384098", categoryId = "281", categoryName = "Airing-Anime", subject = "Overlord II - Diskussionsthread",
        postAmount = 15, hits = 749, firstPostDate = Date(1514199320 * 1000L), firstPostUserId = "351626",
        firstPostUsername = "Asuka..", lastPostDate = Date(1517246199 * 1000L), lastPostUserId = "506979",
        lastPostUsername = "5devilz"
    )

    private val expectedLastForumDiscussion = ForumDiscussion(
        id = "381421", categoryId = "56", categoryName = "Anime- und Manga-News",
        subject = "Overlord II – neues Visual, weitere Charaktere und Synchronsprecher enthüllt", postAmount = 35,
        hits = 32334, firstPostDate = Date(1489228544 * 1000L), firstPostUserId = "19328",
        firstPostUsername = "Moeface", lastPostDate = Date(1514078674 * 1000L), lastPostUserId = "470614",
        lastPostUsername = "..Rhyanon."
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("info_forum.json") {
            api.info
                .forumDiscussions("1")
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedFirstForumDiscussion
        result.last() shouldEqual expectedLastForumDiscussion
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("info_forum.json") {
            api.info.forumDiscussions("3")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/info/forum?id=3"
    }
}
