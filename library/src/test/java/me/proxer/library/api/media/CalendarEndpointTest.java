package me.proxer.library.api.media;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.media.CalendarEntry;
import me.proxer.library.enums.CalendarDay;
import me.proxer.library.enums.Genre;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class CalendarEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("calendar.json")));

        final List<CalendarEntry> result = api.media()
                .calendar()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildFirstTestCalendarEntry());
        assertThat(result).last().isEqualTo(buildLastTestCalendarEntry());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("calendar.json")));

        api.media().calendar()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/media/calendar");
    }

    private CalendarEntry buildFirstTestCalendarEntry() {
        return new CalendarEntry("8843", "21638", "Time Bokan: Gyakushuu no San Akunin", 18,
                "", new Date(1518251400L * 1000), "+09:00", "0", null,
                CalendarDay.SATURDAY, new Date(1518266091L * 1000),
                EnumSet.of(Genre.ADVENTURE, Genre.COMEDY, Genre.MECHA), 7, 2);
    }

    private CalendarEntry buildLastTestCalendarEntry() {
        return new CalendarEntry("8830", "19092", "ClassicaLoid 2nd Season", 17,
                "", new Date(1518251700L * 1000), "+09:00", "308", "NHK",
                CalendarDay.SATURDAY, new Date(1518620676L * 1000),
                EnumSet.of(Genre.COMEDY, Genre.MUSIC), 42, 7);
    }
}
