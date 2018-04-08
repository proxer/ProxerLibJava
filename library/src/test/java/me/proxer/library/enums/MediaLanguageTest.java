package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MediaLanguageTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        MediaLanguage mediaLanguage = api.moshi().adapter(MediaLanguage.class).fromJson("\"de\"");

        assertThat(mediaLanguage).isSameAs(MediaLanguage.GERMAN);
    }

    @Test
    public void testFallback() throws IOException {
        MediaLanguage mediaLanguage = api.moshi().adapter(MediaLanguage.class).fromJson("\"xyz\"");

        assertThat(mediaLanguage).isSameAs(MediaLanguage.OTHER);
    }
}
