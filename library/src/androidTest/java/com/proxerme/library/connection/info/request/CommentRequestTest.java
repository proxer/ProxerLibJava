package com.proxerme.library.connection.info.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.info.entity.Comment;
import com.proxerme.library.connection.info.entity.RatingDetails;
import com.proxerme.library.parameters.CommentSortParameter;
import com.proxerme.library.parameters.CommentStateParameter;
import com.proxerme.library.test.R;
import com.proxerme.library.util.RequestTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;
import static org.junit.Assert.assertEquals;

/**
 * Test for {@link CommentRequest}
 *
 * @author Desnoo
 */
@RunWith(AndroidJUnit4.class)
public class CommentRequestTest extends RequestTest {

    private static final String URL = "/api/v1/info/comments?id=0";
    private static final String PARAMETER_URL =
            "/api/v1/info/comments?id=0&p=0&limit=10&sort=rating";
    private static final String ID = "0";

    @Test
    public void testAnime() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.comment_anime)));

        Comment[] comments = connection.executeSynchronized(new CommentRequest(ID)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestComment(), comments[4]);
    }

    @Test
    public void testEmptyRatingDetails() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.comment_anime)));

        Comment[] comments = connection.executeSynchronized(new CommentRequest(ID)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestCommentEmptyRatingDetails(), comments[8]);
    }

    @Test
    public void testCount() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.comment_anime)));

        Comment[] comments = connection.executeSynchronized(new CommentRequest(ID)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(25, comments.length);
    }

    @Test
    public void testUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.comment_anime)));

        connection.executeSynchronized(new CommentRequest(ID)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(URL, server.takeRequest().getPath());
    }

    @Test
    public void testManga() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.comment_manga)));

        Comment[] comments = connection.executeSynchronized(new CommentRequest(ID)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestMangaComment(), comments[0]);
    }

    @Test
    public void testMangaCount() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.comment_manga)));

        Comment[] comments = connection.executeSynchronized(new CommentRequest(ID)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(1, comments.length);
    }

    @Test
    public void testParameters() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.comment_anime)));

        connection.executeSynchronized(new CommentRequest(ID).withPage(0).withLimit(10)
                .withSortType(CommentSortParameter.RATING)
                .withCustomHost(buildHostUrl(server.url(PARAMETER_URL))));

        assertEquals(PARAMETER_URL, server.takeRequest().getPath());
    }

    private Comment generateTestComment() {
        return new Comment("17835118", "4167", "583403", "entry", CommentStateParameter.WATCHED,
                new RatingDetails(3, 4, 3, 5, 4),
                "Dieser hervorragende Anime hat mich schon am Anfang gefesselt und, am Ende auch " +
                        "nicht enteuscht. Die gut in Szene gesetzte und abwechslungsreiche Story " +
                        "wird je nach Situation, sowol von friedlicher als auch von " +
                        "actionreicher Musik begleitet. Weiterhin sind die Animationen gut " +
                        "gelungen, und die Charaktere ziehen einen mit ihrem Charm in ihren " +
                        "Bann. Daher sollte jeder der auf das Genre steht und, oder sich einen " +
                        "Otaku nennt diesen Anime gesehen haben.",
                8, 25, 0, 1472063559, "Lorak", "583403_pSCA43.jpg");
    }

    private Comment generateTestCommentEmptyRatingDetails() {
        return new Comment("17425831", "4167", "529490", "entry", CommentStateParameter.WATCHED,
                new RatingDetails(0, 0, 0, 0, 0),
                "Mal wieder ein Anime den die casuals ohne Grund in den Himmel hypen.\nFatale " +
                        "Plot-Holes, schlecht geschriebene Charaktere und eine teilweise " +
                        "grauenvolle inszenierung, machen den Anime zu dem haufen schei\u00dfe, " +
                        "der er nun eben ist.\nIch glaube wohl kaum das ich irgendjemanden die " +
                        "(Story) von SAO erl\u00e4utern muss.\nDas machen nur die Plebs, " +
                        "die versuchen ihre schlechten Reviews mit h\u00fclle und f\u00fclle zu " +
                        "best\u00fccken, um im Endeffekt sowieso nicht ernst genommen zu " +
                        "werden.\nFuck off, als ob ich mir die m\u00fche mache so ein Trainwreck " +
                        "auseinander zu nehmen.\nSeht das als Troll an. Aber die wertung ist " +
                        "ernst gemeint.",
                1, 25, 31, 1470583028, "DerLetzteElitist", "529490_Uz63db.jpg");
    }

    private Comment generateTestMangaComment() {
        return new Comment("7502937", "6195", "293356", "entry", CommentStateParameter.WATCHING,
                new RatingDetails(5, 5, 4, 5, 0),
                "Hallo zusammen,\n\nIch wollte nur kurz festhalten, dass dieser Manga einfach - " +
                        "zumindest in meinen Augen - ein Meisterwerk ist! \n\nGenres stimmen - " +
                        "5/5\nStory ist herzerwärmend - 5/5\nZeichenstil ist genial - " +
                        "5/5\nCharaktere sehr ausgeprägt, unterschiedlich, erfrischend, " +
                        "aufeinander abgestimmt 6/5 (ich liebe einfach jeden Charakter)\n\nEcht " +
                        "jetzt, mein Bauch schmerzt schon fast vom vielen Lachen ^_^\nDieser " +
                        "Manga ist echt und wirklich lesenswert. Er erzählt vom alltäglichen " +
                        "Leben von Handa Seishuu, seinem unbewussten Reifen in der Kalligraphie " +
                        "und vielen kleinen Abenteuern, die einfach unbezahlbar und schön " +
                        "mitzuerleben sind ^_^\n\nMeine Empfehlung, einer meiner Top 10 Mangas <3\n" +
                        "\nEure V.K.C.",
                10, 84, 1, 1473258755, "V.K.C.", "293356_5356c84799fed.jpg");
    }
}
