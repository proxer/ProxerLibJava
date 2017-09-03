package me.proxer.library.api.list;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.list.MediaListEntry;
import me.proxer.library.enums.*;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Desnoo
 */
public class MediaSearchEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        final List<MediaListEntry> result = api.list()
                .mediaSearch()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaSearch()
                .name("test")
                .limit(10)
                .page(3)
                .genres(EnumSet.of(Genre.ADULT))
                .excludedGenres(EnumSet.of(Genre.ACTION))
                .tags(new HashSet<>(Arrays.asList("3", "7")))
                .excludedTags(new HashSet<>(Arrays.asList("5", "20")))
                .fskConstraints(EnumSet.of(FskConstraint.FEAR))
                .language(Language.ENGLISH)
                .length(300)
                .lengthBound(LengthBound.LOWER)
                .tagRateFilter(TagRateFilter.RATED_ONLY)
                .tagSpoilerFilter(TagSpoilerFilter.ONLY_SPOILERS)
                .type(MediaType.ALL_MANGA)
                .sort(MediaSearchSortCriteria.CLICKS)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch?name=test&language=en"
                + "&type=all-manga&genre=Adult&nogenre=Action&fsk=fear&sort=clicks&length=300&length-limit=down"
                + "&tags=3%207&notags=5%2020&tagratefilter=rate_1&tagspoilerfilter=spoiler_1&p=3&limit=10");
    }

    @Test
    public void testTagsNull() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaSearch()
                .tags(new HashSet<>(Arrays.asList("3", "7")))
                .tags(null)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch");
    }

    @Test
    public void testExcludedTagsNull() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaSearch()
                .excludedTags(new HashSet<>(Arrays.asList("5", "20")))
                .excludedTags(null)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch");
    }

    private MediaListEntry buildTestEntry() {
        return new MediaListEntry("3637", "+ A Channel", EnumSet.of(Genre.COMEDY, Genre.SCHOOL),
                Medium.OVA, 11, MediaState.FINISHED, 774, 115,
                EnumSet.of(MediaLanguage.ENGLISH_SUB, MediaLanguage.GERMAN_SUB));
    }
}
