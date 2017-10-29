package me.proxer.library.api.list;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.list.Tag;
import me.proxer.library.enums.TagSortCriteria;
import me.proxer.library.enums.TagSubType;
import me.proxer.library.enums.TagType;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class TagListEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("tags.json")));

        final List<Tag> result = api.list()
                .tagList()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestEntry());
        assertThat(result).element(1).isEqualTo(buildSecondTestEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("tags.json")));

        api.list().tagList()
                .name("abc")
                .type(TagType.H_TAG)
                .sortCriteria(TagSortCriteria.ID)
                .sortAscending()
                .subType(TagSubType.FUTURE)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/list/tags?search=abc&type=entry_tag_h"
                + "&sort=id&sort_type=ASC&subtype=zukunft");
    }

    private Tag buildTestEntry() {
        return new Tag("262", TagType.TAG, "4-Koma", "Seitenaufteilung in vier gleich große Panels.",
                TagSubType.DRAWING, false);
    }

    private Tag buildSecondTestEntry() {
        return new Tag("175", TagType.GENRE, "Action", "Dynamische Szenen, spannende Wettkämpfe "
                + "und beeindruckende Kampfszenen prägen dieses Genre.", TagSubType.MISCELLANEOUS, true);
    }
}
