package me.proxer.library.api.list;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.list.MediaListEntry;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Language;
import me.proxer.library.enums.LengthBound;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.MediaSearchSortCriteria;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.MediaType;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.TagRateFilter;
import me.proxer.library.enums.TagSpoilerFilter;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Desnoo
 */
class MediaSearchEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        final List<MediaListEntry> result = api.list()
                .mediaSearch()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaSearch()
                .name("test")
                .limit(10)
                .page(3)
                .tags(new HashSet<>(Arrays.asList("3", "7")))
                .excludedTags(new HashSet<>(Arrays.asList("5", "20")))
                .genres(new HashSet<>(Arrays.asList("22", "33")))
                .excludedGenres(new HashSet<>(Arrays.asList("13", "17")))
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

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch?name=test&language=en&"
                + "type=all-manga&fsk=fear&sort=clicks&length=300&length-limit=down&tags=3%2B7&notags=5%2B20&"
                + "taggenre=22%2B33&notaggenre=13%2B17&tagratefilter=rate_1&tagspoilerfilter=spoiler_1&p=3&limit=10");
    }

    @Test
    void testTagsNull() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaSearch()
                .tags(new HashSet<>(Arrays.asList("3", "7")))
                .tags(null)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch");
    }

    @Test
    void testExcludedTagsNull() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaSearch()
                .excludedTags(new HashSet<>(Arrays.asList("5", "20")))
                .excludedTags(null)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch");
    }

    @Test
    void testGenreTagsNull() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaSearch()
                .genres(new HashSet<>(Arrays.asList("3", "7")))
                .genres(null)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch");
    }

    @Test
    void testExcludedGenreTagsNull() throws Exception {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaSearch()
                .excludedGenres(new HashSet<>(Arrays.asList("5", "20")))
                .excludedGenres(null)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch");
    }

    private MediaListEntry buildTestEntry() {
        return new MediaListEntry("3637", "+ A Channel", new HashSet<>(Arrays.asList("Comedy", "School")),
                Medium.OVA, 11, MediaState.FINISHED, 774, 115,
                EnumSet.of(MediaLanguage.ENGLISH_SUB, MediaLanguage.GERMAN_SUB));
    }
}
