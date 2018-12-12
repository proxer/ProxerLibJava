package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class SynonymTypeTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final SynonymType synonymType = api.moshi().adapter(SynonymType.class).fromJson("\"name\"");

        assertThat(synonymType).isSameAs(SynonymType.ORIGINAL);
    }

    @Test
    void testFallback() throws IOException {
        final SynonymType synonymType = api.moshi().adapter(SynonymType.class).fromJson("\"xyz\"");

        assertThat(synonymType).isSameAs(SynonymType.OTHER);
    }
}
