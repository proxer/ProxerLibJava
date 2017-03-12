package com.proxerme.library.api.list;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.list.MediaListEntry;
import com.proxerme.library.enums.*;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Desnoo
 */
public class MediaListEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        final List<MediaListEntry> result = api.list()
                .mediaList()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntryEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("media_list_entry.json")));

        api.list().mediaList()
                .category(Category.ANIME)
                .medium(Medium.ANIMESERIES)
                .includeHentai(true)
                .searchStart("abc")
                .sort(MediaListSortCriteria.RATING)
                .sortType(MediaListSortType.ASCENDING)
                .limit(10)
                .page(0)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrylist?kat=anime&medium=animeseries" +
                "&isH=true&start=abc&sort=rating&sort_type=ASC&p=0&limit=10");
    }

    private MediaListEntry buildTestEntryEntry() {
        return new MediaListEntry("3637", "+ A Channel", EnumSet.of(Genre.COMEDY, Genre.SCHOOL),
                Medium.OVA, 11, MediaState.FINISHED, 774, 115,
                EnumSet.of(MediaListEntryLanguage.ENGLISH_SUB, MediaListEntryLanguage.GERMAN_SUB));
    }
}
