package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class CountryTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final Country country = api.moshi().adapter(Country.class).fromJson("\"de\"");

        assertThat(country).isSameAs(Country.GERMANY);
    }

    @Test
    void testFallback() throws IOException {
        final Country country = api.moshi().adapter(Country.class).fromJson("\"xyz\"");

        assertThat(country).isSameAs(Country.OTHER);
    }
}
