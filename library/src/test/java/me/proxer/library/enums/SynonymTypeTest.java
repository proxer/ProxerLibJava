package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SynonymTypeTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final SynonymType synonymType = api.moshi().adapter(SynonymType.class).fromJson("\"name\"");

        assertThat(synonymType).isSameAs(SynonymType.ORIGINAL);
    }

    @Test
    public void testFallback() throws IOException {
        final SynonymType synonymType = api.moshi().adapter(SynonymType.class).fromJson("\"xyz\"");

        assertThat(synonymType).isSameAs(SynonymType.OTHER);
    }
}
