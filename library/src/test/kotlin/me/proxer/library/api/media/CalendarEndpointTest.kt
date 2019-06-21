package me.proxer.library.api.media

import me.proxer.library.ProxerTest
import me.proxer.library.entity.media.CalendarEntry
import me.proxer.library.enums.CalendarDay
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class CalendarEndpointTest : ProxerTest() {

    private val firstExpectedEntry = CalendarEntry(
        id = "8843", entryId = "21638", name = "Time Bokan: Gyakushuu no San Akunin", episode = 18, episodeTitle = "",
        date = Date(1518251400L * 1000), timezone = "+09:00", industryId = "0", industryName = null,
        weekDay = CalendarDay.SATURDAY, uploadDate = Date(1518266091L * 1000),
        genres = setOf("Adventure", "Comedy", "Mecha"), ratingSum = 7, ratingAmount = 2
    )

    private val secondExpectedEntry = CalendarEntry(
        id = "8830", entryId = "19092", name = "ClassicaLoid 2nd Season", episode = 17, episodeTitle = "",
        date = Date(1518251700L * 1000), timezone = "+09:00", industryId = "308", industryName = "NHK",
        weekDay = CalendarDay.SATURDAY, uploadDate = Date(1518620676L * 1000), genres = setOf("Comedy", "Musik"),
        ratingSum = 42, ratingAmount = 7
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("calendar.json") {
            api.media
                .calendar()
                .build()
                .safeExecute()
        }

        result.first() shouldEqual firstExpectedEntry
        result.last() shouldEqual secondExpectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("calendar.json") {
            api.media.calendar()
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/media/calendar"
    }
}
