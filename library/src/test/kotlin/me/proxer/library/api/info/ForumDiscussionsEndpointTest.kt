package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.ForumDiscussion
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ForumDiscussionsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("info_forum.json")))

        val result = api.info
            .forumDiscussions("1")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildFirstTestForumDiscussion())
        assertThat(result).last().isEqualTo(buildLastTestForumDiscussion())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("info_forum.json")))

        api.info.forumDiscussions("3")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/forum?id=3")
    }

    private fun buildFirstTestForumDiscussion(): ForumDiscussion {
        return ForumDiscussion(
            "384098", "281", "Airing-Anime", "Overlord II - Diskussionsthread",
            15, 749, Date(1514199320 * 1000L), "351626", "Asuka..",
            Date(1517246199 * 1000L), "506979", "5devilz"
        )
    }

    private fun buildLastTestForumDiscussion(): ForumDiscussion {
        return ForumDiscussion(
            "381421", "56", "Anime- und Manga-News",
            "Overlord II – neues Visual, weitere Charaktere und Synchronsprecher enthüllt",
            35, 32334, Date(1489228544 * 1000L), "19328", "Moeface",
            Date(1514078674 * 1000L), "470614", "..Rhyanon."
        )
    }
}
