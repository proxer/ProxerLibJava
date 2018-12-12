package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class AnimeLanguageTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final AnimeLanguage animeLanguage = api.moshi().adapter(AnimeLanguage.class).fromJson("\"gersub\"");

        assertThat(animeLanguage).isSameAs(AnimeLanguage.GERMAN_SUB);
    }

    @Test
    void testFallback() throws IOException {
        final AnimeLanguage animeLanguage = api.moshi().adapter(AnimeLanguage.class).fromJson("\"xyz\"");

        assertThat(animeLanguage).isSameAs(AnimeLanguage.OTHER);
    }
}
