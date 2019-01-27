package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.AdaptionInfo
import me.proxer.library.entity.info.EntryCore
import me.proxer.library.enums.Category
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.License
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Arrays
import java.util.EnumSet
import java.util.HashSet

/**
 * @author Ruben Gees
 */
class EntryCoreEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("entry_core.json")))

        val result = api.info
            .entryCore("1")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("entry.json")))

        api.info.entryCore("1")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/entry?id=1")
    }

    private fun buildTestEntry(): EntryCore {
        return EntryCore(
            "6174", "LuCu LuCu",
            HashSet(Arrays.asList("Comedy", "Fantasy", "Seinen", "Slice of Life")),
            EnumSet.of(FskConstraint.BAD_LANGUAGE),
            "Humans are a despicable lot, committing sin after sin, filling the endless boundaries "
                + "of the underworld with tortured souls. Now, it would seem, Hell isn't so endless after all, "
                + "and it has become dangerously close to filling, and then overflowing into the human realm. "
                + "Princess Lucuha sees this imminent disaster and has a plan: save Hell by making humans "
                + "decent again. Of course, the angels can't simply allow demons to roam freely on Earth, "
                + "and they do their best to stop Lucu and her dastardly plans.",
            Medium.MANGASERIES, 90, MediaState.FINISHED, 7, 1, 134,
            Category.MANGA, License.NOT_LICENSED,
            AdaptionInfo("2793", "KissXsis", Medium.MANGASERIES)
        )
    }
}
