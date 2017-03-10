package com.proxerme.library.api.messenger;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.messenger.ConferenceInfo;
import com.proxerme.library.entitiy.messenger.ConferenceParticipant;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class ConferenceInfoEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_info.json")));

        final ConferenceInfo result = api.messenger()
                .conferenceInfo("1")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestConferenceInfo());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("conference_info.json")));

        api.messenger().conferenceInfo("1")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/messenger/conferenceinfo?conference_id=1");
    }

    private ConferenceInfo buildTestConferenceInfo() {
        return new ConferenceInfo("Ein weiterer Test", 2, new Date(1480260735L * 1000),
                new Date(1487080743L * 1000), "121658", Arrays.asList(
                new ConferenceParticipant("121658", "121658_cEBC8F.png", "RubyGee",
                        "Ihr könnt mich jederzeit anschreiben, Skype oder ProxerPn!"),
                new ConferenceParticipant("574520", "574520_K4DfDC.jpg", "Testerio",
                        "Ich bin Rubys Sklave~")
        ));
    }
}
