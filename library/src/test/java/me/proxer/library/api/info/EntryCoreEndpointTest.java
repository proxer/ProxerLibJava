package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entitiy.info.EntryCore;
import me.proxer.library.enums.*;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class EntryCoreEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("entry_core.json")));

        final EntryCore result = api.info()
                .entryCore("1")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("entry.json")));

        api.info().entryCore("1")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/entry?id=1");
    }

    private EntryCore buildTestEntry() {
        return new EntryCore("6174", "LuCu LuCu",
                EnumSet.of(Genre.COMEDY, Genre.FANTASY, Genre.SEINEN, Genre.SLICE_OF_LIFE),
                EnumSet.of(FskConstraint.BAD_LANGUAGE),
                "Humans are a despicable lot, committing sin after sin, filling the endless boundaries " +
                        "of the underworld with tortured souls. Now, it would seem, Hell isn't so endless after all, " +
                        "and it has become dangerously close to filling, and then overflowing into the human realm. " +
                        "Princess Lucuha sees this imminent disaster and has a plan: save Hell by making humans " +
                        "decent again. Of course, the angels can't simply allow demons to roam freely on Earth, " +
                        "and they do their best to stop Lucu and her dastardly plans.",
                Medium.MANGASERIES, 90, MediaState.FINISHED, 7, 1, 134,
                Category.MANGA, License.NOT_LICENSED);
    }
}
