package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class LanguageTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final Language language = api.moshi().adapter(Language.class).fromJson("\"de\"");

        assertThat(language).isSameAs(Language.GERMAN);
    }

    @Test
    void testFallback() throws IOException {
        final Language language = api.moshi().adapter(Language.class).fromJson("\"XYZ\"");

        assertThat(language).isSameAs(Language.OTHER);
    }
}
