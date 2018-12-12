package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class LicenseTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final License license = api.moshi().adapter(License.class).fromJson("\"2\"");

        assertThat(license).isSameAs(License.LICENSED);
    }

    @Test
    void testFallback() throws IOException {
        final License license = api.moshi().adapter(License.class).fromJson("\"xyz\"");

        assertThat(license).isSameAs(License.UNKNOWN);
    }
}
