package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class MediaLanguageTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final MediaLanguage mediaLanguage = api.moshi().adapter(MediaLanguage.class).fromJson("\"de\"");

        assertThat(mediaLanguage).isSameAs(MediaLanguage.GERMAN);
    }

    @Test
    void testFallback() throws IOException {
        final MediaLanguage mediaLanguage = api.moshi().adapter(MediaLanguage.class).fromJson("\"xyz\"");

        assertThat(mediaLanguage).isSameAs(MediaLanguage.OTHER);
    }
}
