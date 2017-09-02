package me.proxer.library.api;

import me.proxer.library.api.ConferenceAdapter.IntermediateConference;
import me.proxer.library.entitiy.messenger.Conference;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class ConferenceAdapterTest {

    private ConferenceAdapter adapter;

    @Before
    public void setUp() {
        adapter = new ConferenceAdapter();
    }

    @Test
    public void testFromJson() {
        assertThat(adapter.fromJson(constructIntermediateConference(true)))
                .isEqualTo(constructConference(true));
    }

    @Test
    public void testFromJsonWithNoImage() {
        assertThat(adapter.fromJson(constructIntermediateConference(false)))
                .isEqualTo(constructConference(false));
    }

    private IntermediateConference constructIntermediateConference(final boolean withImage) {
        IntermediateConference result = new IntermediateConference();

        result.id = "123";
        result.topic = "something";
        result.customTopic = "somethingElse";
        result.participantAmount = 13;
        result.group = true;
        result.read = false;
        result.date = new Date(123456L);
        result.unreadMessageAmount = 2;
        result.lastReadMessageId = "321";
        result.image = withImage ? "avatar:http://example.com/image.png" : "";

        return result;
    }

    private Conference constructConference(final boolean withImage) {
        return new Conference("123", "something", "somethingElse", 13,
                withImage ? "http://example.com/image.png" : "", withImage ? "avatar" : "", true, false,
                new Date(123456L), 2, "321");
    }
}
