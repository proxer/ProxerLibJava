package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class GenderTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final Gender gender = api.moshi().adapter(Gender.class).fromJson("\"f\"");

        assertThat(gender).isSameAs(Gender.FEMALE);
    }

    @Test
    public void testFallback() throws IOException {
        final Gender gender = api.moshi().adapter(Gender.class).fromJson("\"xyz\"");

        assertThat(gender).isSameAs(Gender.UNKNOWN);
    }
}
