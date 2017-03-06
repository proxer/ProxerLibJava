package com.proxerme.library.api.info;

import com.proxerme.library.ApiTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.info.Comment;
import com.proxerme.library.entitiy.info.RatingDetails;
import com.proxerme.library.enums.UserMediaProgress;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class CommentsEndpointTest extends ApiTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("comment_anime.json")));

        final List<Comment> result = api.info()
                .comments("1")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestComment());
    }

    private Comment buildTestComment() {
        return new Comment("20405741", "5840", "635753", UserMediaProgress.WATCHED,
                new RatingDetails(0, 0, 0, 0, 0),
                "Ich fasse es kurz zusammen: Dieser Anime hat Stand Heute den Titel Meisterwerk wahrlich " +
                        "verdient. Atmosphäre, Story, Personen, alles ist meiner Meinung nach gelungen. Den " +
                        "einzigen Kritikpunkt den ich anzumerken hätte ist das offene Ende. Da sich meine " +
                        "Bewertung allerdings auf den Anime an sich bezieht, ist das für mich eher nicht " +
                        "relevant zumal ja für dieses Jahr auch endlich die Zweite Staffel angekündigt wurde. " +
                        "Ich kann also aktuell jeden empfehlen diesen Anime zu schauen. ",
                10, 0, 0, new Date(1487696018L * 1000), "Hackl24",
                "");
    }
}
