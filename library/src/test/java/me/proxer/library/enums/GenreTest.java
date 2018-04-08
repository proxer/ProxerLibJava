package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GenreTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        Genre genre = api.moshi().adapter(Genre.class).fromJson("\"Action\"");

        assertThat(genre).isSameAs(Genre.ACTION);
    }

    @Test
    public void testFallback() throws IOException {
        Genre genre = api.moshi().adapter(Genre.class).fromJson("\"xyz\"");

        assertThat(genre).isSameAs(Genre.UNKNOWN);
    }
}
