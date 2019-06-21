package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.AdaptionInfo
import me.proxer.library.entity.info.EntryCore
import me.proxer.library.enums.Category
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.License
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class EntryCoreEndpointTest : ProxerTest() {

    private val expectedEntry = EntryCore(
        id = "6174", name = "LuCu LuCu", genres = setOf("Comedy", "Fantasy", "Seinen", "Slice of Life"),
        fskConstraints = setOf(FskConstraint.BAD_LANGUAGE),
        description = "Humans are a despicable lot, committing sin after sin, filling the endless boundaries " +
            "of the underworld with tortured souls. Now, it would seem, Hell isn't so endless after all, " +
            "and it has become dangerously close to filling, and then overflowing into the human realm. " +
            "Princess Lucuha sees this imminent disaster and has a plan: save Hell by making humans " +
            "decent again. Of course, the angels can't simply allow demons to roam freely on Earth, " +
            "and they do their best to stop Lucu and her dastardly plans.",
        medium = Medium.MANGASERIES, episodeAmount = 90, state = MediaState.FINISHED, ratingSum = 7,
        ratingAmount = 1, clicks = 134, category = Category.MANGA, license = License.NOT_LICENSED,
        adaptionInfo = AdaptionInfo(id = "2793", name = "KissXsis", medium = Medium.MANGASERIES)
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("entry_core.json") {
            api.info
                .entryCore("1")
                .build()
                .execute()
        }

        result shouldEqual expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("entry.json") {
            api.info.entryCore("1")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/info/entry?id=1"
    }
}
