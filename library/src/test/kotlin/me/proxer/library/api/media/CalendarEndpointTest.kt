package me.proxer.library.api.media

import me.proxer.library.ProxerTest
import me.proxer.library.entity.media.CalendarEntry
import me.proxer.library.enums.CalendarDay
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class CalendarEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("calendar.json")))

        val result = api.media
            .calendar()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildFirstTestCalendarEntry())
        assertThat(result).last().isEqualTo(buildLastTestCalendarEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("calendar.json")))

        api.media.calendar()
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/media/calendar")
    }

    private fun buildFirstTestCalendarEntry(): CalendarEntry {
        return CalendarEntry(
            "8843", "21638", "Time Bokan: Gyakushuu no San Akunin", 18,
            "", Date(1518251400L * 1000), "+09:00", "0", null,
            CalendarDay.SATURDAY, Date(1518266091L * 1000), setOf("Adventure", "Comedy", "Mecha"), 7, 2
        )
    }

    private fun buildLastTestCalendarEntry(): CalendarEntry {
        return CalendarEntry(
            "8830", "19092", "ClassicaLoid 2nd Season", 17,
            "", Date(1518251700L * 1000), "+09:00", "308", "NHK",
            CalendarDay.SATURDAY, Date(1518620676L * 1000), setOf("Comedy", "Musik"), 42, 7
        )
    }
}
