package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class GenderTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final Gender gender = api.moshi().adapter(Gender.class).fromJson("\"f\"");

        assertThat(gender).isSameAs(Gender.FEMALE);
    }

    @Test
    void testFallback() throws IOException {
        final Gender gender = api.moshi().adapter(Gender.class).fromJson("\"xyz\"");

        assertThat(gender).isSameAs(Gender.UNKNOWN);
    }
}
