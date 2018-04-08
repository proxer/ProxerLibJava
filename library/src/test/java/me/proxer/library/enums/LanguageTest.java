package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class LanguageTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        Language language = api.moshi().adapter(Language.class).fromJson("\"de\"");

        assertThat(language).isSameAs(Language.GERMAN);
    }

    @Test
    public void testFallback() throws IOException {
        Language language = api.moshi().adapter(Language.class).fromJson("\"XYZ\"");

        assertThat(language).isSameAs(Language.OTHER);
    }
}
