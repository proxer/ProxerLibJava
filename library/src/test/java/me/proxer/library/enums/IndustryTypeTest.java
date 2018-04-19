package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class IndustryTypeTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final IndustryType industryType = api.moshi().adapter(IndustryType.class).fromJson("\"publisher\"");

        assertThat(industryType).isSameAs(IndustryType.PUBLISHER);
    }

    @Test
    public void testFallback() throws IOException {
        final IndustryType industryType = api.moshi().adapter(IndustryType.class).fromJson("\"xyz\"");

        assertThat(industryType).isSameAs(IndustryType.UNKNOWN);
    }
}
