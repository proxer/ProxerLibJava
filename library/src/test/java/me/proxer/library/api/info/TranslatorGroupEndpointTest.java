package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entitiy.info.TranslatorGroup;
import me.proxer.library.enums.Country;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class TranslatorGroupEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group.json")));

        final TranslatorGroup result = api.info()
                .translatorGroup("4")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestGroup());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("translator_group.json")));

        api.info().translatorGroup("12")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/translatorgroup?id=12");
    }

    private TranslatorGroup buildTestGroup() {
        return new TranslatorGroup("11", "Gruppe Kampfkuchen", Country.GERMANY,
                "http://i.imgur.com/hBoT4Ax.png", HttpUrl.parse("http://kampfkuchen.de"),
                "Gruppe Kampfkuchen, die Gruppe für qualitativ hochwertige Lolisubs! Qualität vor "
                        + "Quantität! Besucht uns doch mal in #Kampfkuchen auf irc.otakubox.de!\r\n\r\nGK ist "
                        + "mittlerweile drei Jahre alt! Schaut doch mal bei uns im Forum oder auf der Page vorbei."
                        + "\r\n\r\nWir sind auch immer froh um Hilfe beim Subben. Wer also denkt, er könne was oder "
                        + "wer gerne was lernen möchte, darf sich ruhig bei uns im Chan melden. Wir beißen nicht... "
                        + "Jedenfalls mindestens einen Tag im Jahr nicht.", 5087, 23);
    }
}
