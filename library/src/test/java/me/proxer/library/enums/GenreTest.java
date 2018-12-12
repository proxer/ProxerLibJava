package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class GenreTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final Genre genre = api.moshi().adapter(Genre.class).fromJson("\"Action\"");

        assertThat(genre).isSameAs(Genre.ACTION);
    }

    @Test
    void testFallback() throws IOException {
        final Genre genre = api.moshi().adapter(Genre.class).fromJson("\"xyz\"");

        assertThat(genre).isSameAs(Genre.UNKNOWN);
    }
}
