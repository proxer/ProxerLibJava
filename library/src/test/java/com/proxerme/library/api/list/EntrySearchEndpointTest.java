package com.proxerme.library.api.list;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.info.Comment;
import com.proxerme.library.entitiy.info.RatingDetails;
import com.proxerme.library.entitiy.list.MediaEntry;
import com.proxerme.library.enums.CommentSortCriteria;
import com.proxerme.library.enums.MediaState;
import com.proxerme.library.enums.Medium;
import com.proxerme.library.enums.UserMediaProgress;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Desnoo
 */
public class EntrySearchEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("media_entry.json")));

        final List<MediaEntry> result = api.list()
                .searchEntries(0)
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestMediaEntry());
    }


    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("media_entry.json")));

        api.list()
            .searchEntries(0)
            .build()
            .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/entrysearch?p=0");
    }

    private MediaEntry buildTestMediaEntry() {
        return new MediaEntry("3637", "+ A Channel", "Comedy School",
                Medium.OVA, 11, MediaState.FINISHED, 774, 115, "gersub,engsub");
    }
}
