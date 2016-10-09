package com.proxerme.library.connection.info.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.info.entity.Entry;
import com.proxerme.library.connection.info.entity.EntrySeason;
import com.proxerme.library.connection.info.entity.Publisher;
import com.proxerme.library.connection.info.entity.Subgroup;
import com.proxerme.library.connection.info.entity.Synonym;
import com.proxerme.library.connection.info.entity.Tag;
import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.LicenseParameter;
import com.proxerme.library.parameters.MediumParameter;
import com.proxerme.library.parameters.SeasonParameter;
import com.proxerme.library.parameters.StateParameter;
import com.proxerme.library.parameters.SynonymTypeParameter;
import com.proxerme.library.test.R;
import com.proxerme.library.util.RequestTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;
import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link EntryRequest}.
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class EntryRequestTest extends RequestTest {

    private static final String URL = "/api/v1/info/fullentry?id=100";
    private static final String ID = "100";

    @Test
    public void testDefault() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.entry)));

        Entry result = connection.executeSynchronized(new EntryRequest(ID)
                .withCustomHost(buildHostUrl(server.url(URL))));

        Entry test = generateTestEntry();

        assertEquals(test, result);
    }

    @Test
    public void testDefaultUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.entry)));

        connection.executeSynchronized(new EntryRequest(ID)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(URL, server.takeRequest().getPath());
    }

    private Entry generateTestEntry() {
        return new Entry(ID, "Akane-Iro ni Somaru Saka", "Comedy Ecchi Romance Harem School",
                "fsk12", "Yuuhi zieht in eine neue Stadt, doch schon der erste Tag wird ein " +
                "wahres Desaster. Zuerst wird sie von Schlägern bedroht, jedoch von einem ihrer " +
                "Klassenkameraden gerettet. Dieser stiehlt ihr wenig später ihren ersten Kuss " +
                "und anschließend stellt sich heraus, dass er ihr zukünftiger Verlobter ist. " +
                "Doch Yuuhi gefällt ihr zukünftiger Partner gar nicht und so versucht sie ihre " +
                "Eltern davon zu überzeugen, dass er doch keine so gute Wahl war...",
                MediumParameter.ANIMESERIES, 12, StateParameter.FINISHED, 15998, 2300, 3390,
                CategoryParameter.ANIME, LicenseParameter.NON_LICENSED, false,
                new Synonym[]{
                        new Synonym("262", ID, SynonymTypeParameter.ORIGINAL,
                                "Akane-Iro ni Somaru Saka"),
                        new Synonym("263", ID, SynonymTypeParameter.ENGLISH,
                                "The Hill Dyed Rose Madder"),
                        new Synonym("264", ID, SynonymTypeParameter.JAPANESE,
                                "あかね色に染まる坂"),
                },
                new String[]{
                        "gersub",
                        "engsub",
                },
                new EntrySeason[]{
                        new EntrySeason("2940", 2008, SeasonParameter.AUTUMN),
                },
                new Subgroup[]{
                        new Subgroup("8", "Strawhat Subs", "de"),
                        new Subgroup("137", "Anime-Crystal", "de"),},
                new Publisher[]{
                        new Publisher("101", "TNK", "studio", "jp"),
                }, new Tag[]{
                new Tag("12", "2710", "2016-06-18 18:09:59", 0, 0, "Incest", "Romantische " +
                        "Gefühle und/oder sexuelle Handlungen unter nahen Verwandten."),
                new Tag("242", "8206", "2016-07-14 14:56:31", 0, 0, "Protagonist", "Die " +
                        "Hauptrolle wird von einer männlichen Person besetzt."),
                new Tag("105", "8207", "2016-07-14 14:56:39", 0, 0, "Tsundere", "Ein Charakter, " +
                        "der in der Öffentlichkeit vor allem aggressiv gegenüber dem Geliebten " +
                        "eingestellt ist, bei Zweisamkeit mit ihm aber schüchtern und " +
                        "zurückhaltend wird, steht im Fokus."),
        });
    }
}
