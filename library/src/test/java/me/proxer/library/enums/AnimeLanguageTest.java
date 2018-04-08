package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AnimeLanguageTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        AnimeLanguage animeLanguage = api.moshi().adapter(AnimeLanguage.class).fromJson("\"gersub\"");

        assertThat(animeLanguage).isSameAs(AnimeLanguage.GERMAN_SUB);
    }

    @Test
    public void testFallback() throws IOException {
        AnimeLanguage animeLanguage = api.moshi().adapter(AnimeLanguage.class).fromJson("\"xyz\"");

        assertThat(animeLanguage).isSameAs(AnimeLanguage.OTHER);
    }
}
