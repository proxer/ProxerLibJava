package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.Comment
import me.proxer.library.entity.info.RatingDetails
import me.proxer.library.enums.CommentSortCriteria
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class CommentsEndpointTest : ProxerTest() {

    private val expectedComment = Comment(
        id = "7502937", entryId = "6195", authorId = "293356", mediaProgress = UserMediaProgress.WATCHING,
        ratingDetails = RatingDetails(genre = 5, story = 5, animation = 4, characters = 5, music = 0),
        content = "Hallo zusammen,\n\nIch wollte nur kurz festhalten, dass dieser Manga einfach - zumindest " +
            "in meinen Augen - ein Meisterwerk ist! \n\nGenres stimmen - 5/5\nStory ist " +
            "herzerwärmend - 5/5\nZeichenstil ist genial - 5/5\nCharaktere sehr " +
            "ausgeprägt, unterschiedlich, erfrischend, aufeinander abgestimmt 6/5 " +
            "(ich liebe einfach jeden Charakter)\n\nEcht jetzt, mein Bauch schmerzt schon fast vom " +
            "vielen Lachen ^_^\nDieser Manga ist echt und wirklich lesenswert. Er erzählt vom " +
            "alltäglichen Leben von Handa Seishuu, seinem unbewussten Reifen in der Kalligraphie " +
            "und vielen kleinen Abenteuern, die einfach unbezahlbar und schön mitzuerleben sind " +
            "^_^\n\nMeine Empfehlung, einer meiner Top 10 Mangas <3\n\nEure V.K.C.",
        overallRating = 10, episode = 84, helpfulVotes = 1, date = Date(1473258755L * 1000), author = "V.K.C.",
        image = "293356_5356c84799fed.jpg"
    )

    private val expectedCommentWithEmptyRatingDetails = Comment(
        id = "20405741", entryId = "5840", authorId = "635753", mediaProgress = UserMediaProgress.WATCHED,
        ratingDetails = RatingDetails(0, 0, 0, 0, 0),
        content = "Ich fasse es kurz zusammen: Dieser Anime hat Stand Heute den Titel Meisterwerk wahrlich " +
            "verdient. Atmosphäre, Story, Personen, alles ist meiner Meinung nach gelungen. Den " +
            "einzigen Kritikpunkt den ich anzumerken hätte ist das offene Ende. Da sich meine " +
            "Bewertung allerdings auf den Anime an sich bezieht, ist das für mich eher nicht " +
            "relevant zumal ja für dieses Jahr auch endlich die Zweite Staffel angekündigt wurde. " +
            "Ich kann also aktuell jeden empfehlen diesen Anime zu schauen. ",
        overallRating = 10, episode = 0, helpfulVotes = 0, date = Date(1487696018L * 1000), author = "Hackl24",
        image = ""
    )

    private val expectedCommentWithOtherEmptyRatingDetails = Comment(
        "715699", "4167", "30647", UserMediaProgress.WATCHED,
        RatingDetails(0, 0, 0, 0, 0),
        "Ich habe dem Anime richtig entgegen gefiebert. Ich hatte mir die Promovideos angesehen und " +
            "hatte mich daher gefreut, als dann endlich die erste Folge rauskam. \r\nUnd sie hatte" +
            " mich wirklich nicht enttäuscht. \r\nIch habe auch den Manga gelesen und deshalb bin " +
            "ich gespannt, wie sie es nun umsetzten. \r\nAber es wird schon einige Unterschiede zum " +
            "Manga geben, da man im Trailer doch einige Unterschiede erkennen konnte.\r\n\r\nGenerell " +
            "finde ich die Idee des Mangas/Animes sehr gut. Wenn man so zuschaut, würde man auch gerne " +
            "selber so ein Spiel ausprobieren. Natürlich ohne solche Komplikationen :D\r\n\r\nDie Musik " +
            "gefiel mir sehr gut und auch die Animation und die Story. \r\nIch denke mal, dass Swords " +
            "Art Online der beste Anime der Saison werden wird. Ich bin schon auf die nächsten Folgen " +
            "gespannt.\r\n\r\nIch bin jetzt fertig und der Anime war wirklich gut, bis auf das Ende... " +
            "das war nicht so mein Ding, aber trotzdem ein sehr guter Anime.",
        7, 25, 67, Date(1382548071L * 1000), "Bramble",
        "30647_51b789443be90.jpg"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("comment_manga.json") {
            api.info
                .comments("1")
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedComment
    }

    @Test
    fun testEmptyRatingDetails() {
        val (result, _) = server.runRequest("comment_anime.json") {
            api.info
                .comments("1")
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedCommentWithEmptyRatingDetails
    }

    @Test
    fun testEmptyRatingDetailsOther() {
        val (result, _) = server.runRequest("comment_anime_other.json") {
            api.info
                .comments("1")
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedCommentWithOtherEmptyRatingDetails
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("comment_manga.json") {
            api.info.comments("1")
                .page(0)
                .limit(10)
                .sort(CommentSortCriteria.RATING)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/info/comments?id=1&p=0&limit=10&sort=rating"
    }
}
