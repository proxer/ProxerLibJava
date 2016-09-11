package com.proxerme.library.connection.info.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.info.entity.Comment;
import com.proxerme.library.parameters.CommentSortParameter;
import com.proxerme.library.parameters.CommentStateParameter;
import com.proxerme.library.connection.info.entity.RatingDetails;
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
        Comment[] comments = connection.executeSynchronized(new CommentRequest(PAGE)
                                                                    .withCustomHost(buildHostUrl(server.url(URL))));
        assertEquals(25, comments[0].getEpisode());
        RatingDetails details = comments[0].getRatingDetails();
        assertEquals(3, details.getGenre());
        assertEquals(4, details.getStory());
        assertEquals(3, details.getAnimation());
        assertEquals(5, details.getCharacters());
        assertEquals(4, details.getMusic());

        assertEquals(generateTestComment(), comments[0]);
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
        Comment[] comments = connection.executeSynchronized(new CommentRequest(PAGE)
                                                                    .withCustomHost(buildHostUrl(server.url(URL))));
        assertEquals(63, comments[0].getEpisode());
        RatingDetails details = comments[0].getRatingDetails();
        assertEquals(5, details.getGenre());
        assertEquals(4, details.getAnimation());
        assertEquals(5, details.getCharacters());
        assertEquals(5, details.getStory());
        assertEquals(0, details.getMusic());
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
                "Dieser hervorragende Anime hat mich schon am Anfang gefesselt und, am Ende auch " +
                        "nicht enteuscht. Die gut in Szene gesetzte und abwechslungsreiche Story " +
                        "wird je nach Situation, sowol von friedlicher als auch von " +
                        "actionreicher Musik begleitet. Weiterhin sind die Animationen gut " +
                        "gelungen, und die Charaktere ziehen einen mit ihrem Charm in ihren " +
                        "Bann. Daher sollte jeder der auf das Genre steht und, oder sich einen " +
                        "Otaku nennt diesen Anime gesehen haben.", 8, 25, 0, 1472063559, "Lorak",
                "583403_pSCA43.jpg");
    }

    private Comment generateTestMangaComment() {
        return new Comment("7502937", "6195", "293356", "entry", CommentStateParameter.WATCHING,
                "Hallo zusammen,\n\nIch wollte nur kurz festhalten, dass dieser Manga einfach " +
                        "- zumindest in meinen Augen - ein Meisterwerk ist! " +
                        "\n\nGenres stimmen - 5/5\nStory ist herzerwärmend - " +
                        "5/5\nZeichenstil ist genial - 5/5\nCharaktere sehr ausgeprägt, " +
                        "unterschiedlich, erfrischend, aufeinander abgestimmt 6/5 (ich liebe " +
                        "einfach jeden Charakter)\n\nEcht jetzt, mein Bauch schmerzt schon " +
                        "fast vom vielen Lachen ^_^\nDieser Manga ist echt und wirklich " +
                        "lesenswert. Er erzählt vom alltäglichen Leben von Handa Seishuu, " +
                        "seinem unbewussten Reifen in der Kalligraphie und vielen kleine " +
                        "Abenteuer, die einfach unbezahlbar und schön mitzuerleben sind " +
                        "^_^\n\nMeine Empfehlung, einer meiner Top 10 Mangas " +
                        "<3\n\nEure V.K.C.", 10, 63, 1, 1460844261, "V.K.C.",
                "293356_5356c84799fed.jpg");
    }
}
