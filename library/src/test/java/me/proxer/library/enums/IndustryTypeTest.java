package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class IndustryTypeTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final IndustryType industryType = api.moshi().adapter(IndustryType.class).fromJson("\"publisher\"");

        assertThat(industryType).isSameAs(IndustryType.PUBLISHER);
    }

    @Test
    void testFallback() throws IOException {
        final IndustryType industryType = api.moshi().adapter(IndustryType.class).fromJson("\"xyz\"");

        assertThat(industryType).isSameAs(IndustryType.UNKNOWN);
    }
}
