package com.proxerme.library.connection.info.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.info.entity.Comment;
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

    private static final String URL = "api/v1/info/comments";
    private static final String PAGE = "0";

    @Test
    public void testAnime() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(loadResponse(com.proxerme.library.test.R.raw.comment_anime)));
        Comment[] comments = connection.executeSynchronized(new CommentRequest(PAGE)
                                                                    .withCustomHost(buildHostUrl(server.url(URL))));
        assertEquals(25, comments[0].getEpisode());
//        RatingDetails details = comments[0].getRatingDetails();
//        assertEquals(3, details.getGenre());
//        assertEquals(3, details.getAnimation());
//        assertEquals(5, details.getCharacters());
//        assertEquals(4, details.getStory());
//        assertEquals(4, details.getMusic());

    }

    @Test
    public void testManga() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(loadResponse(com.proxerme.library.test.R.raw.comment_manga)));
        Comment[] comments = connection.executeSynchronized(new CommentRequest("0")
                                                                    .withCustomHost(buildHostUrl(server.url(URL))));
        assertEquals(63, comments[0].getEpisode());
//        RatingDetails details = comments[0].getRatingDetails();
//        assertEquals(5, details.getGenre());
//        assertEquals(4, details.getAnimation());
//        assertEquals(5, details.getCharacters());
//        assertEquals(5, details.getStory());
//        assertEquals(0, details.getMusic());
    }
}
