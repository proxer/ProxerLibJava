package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SeasonTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final Season season = api.moshi().adapter(Season.class).fromJson("\"1\"");

        assertThat(season).isSameAs(Season.WINTER);
    }

    @Test
    public void testFallback() throws IOException {
        final Season season = api.moshi().adapter(Season.class).fromJson("\"xyz\"");

        assertThat(season).isSameAs(Season.UNSPECIFIED);
    }
}
