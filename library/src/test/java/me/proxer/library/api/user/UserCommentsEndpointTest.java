package me.proxer.library.api.user;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.info.RatingDetails;
import me.proxer.library.entity.user.UserComment;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.UserMediaProgress;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author Ruben Gees
 */
public class UserCommentsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("user_comment.json")));

        final List<UserComment> result = api.user()
                .comments("123", "abc")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestComment());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("user_comment.json")));

        api.user().comments("123", "abc")
                .page(3)
                .limit(12)
                .minimumLength(1234)
                .category(Category.ANIME)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/user/comments?uid=123&username=abc&kat=anime"
                + "&p=3&limit=12&length=1234");
    }

    @Test
    public void testIllegalArguments() {
        final InternalApi internalApi = new Retrofit.Builder()
                .baseUrl("http://example.com")
                .build()
                .create(InternalApi.class);

        //noinspection ConstantConditions
        assertThatThrownBy(() -> new UserCommentsEndpoint(internalApi, null, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private UserComment buildTestComment() {
        return new UserComment("1106146", "4704", "Sakurasou no Pet na Kanojo", Medium.ANIMESERIES,
                Category.ANIME, "62", UserMediaProgress.CANCELLED,
                new RatingDetails(0, 0, 0, 0, 0), "Der Anfang ok... aber "
                + "dann... schrecklich... Oh mein Gott der Anime ist meiner Meinung nach unschaubar D:\nHabe es nach "
                + "10 Episoden abgebrochen... Habs einfach nicht mehr durchgehalten -_- Diese Beziehungsscheiße ist "
                + "schlimmer als bei jeder Horror Anime... Sowas kann ich einfach nicht schauen. \n\n\nTut mir leid, "
                + "I'm out.", 2, 9, 8, new Date(1391476364L * 1000), "genesis",
                "62_L36C3N.png");
    }
}
