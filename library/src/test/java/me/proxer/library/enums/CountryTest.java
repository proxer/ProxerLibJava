package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CountryTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final Country country = api.moshi().adapter(Country.class).fromJson("\"de\"");

        assertThat(country).isSameAs(Country.GERMANY);
    }

    @Test
    public void testFallback() throws IOException {
        final Country country = api.moshi().adapter(Country.class).fromJson("\"xyz\"");

        assertThat(country).isSameAs(Country.OTHER);
    }
}
