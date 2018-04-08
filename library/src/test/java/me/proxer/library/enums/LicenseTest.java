package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class LicenseTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        License license = api.moshi().adapter(License.class).fromJson("\"2\"");

        assertThat(license).isSameAs(License.LICENSED);
    }

    @Test
    public void testFallback() throws IOException {
        License license = api.moshi().adapter(License.class).fromJson("\"xyz\"");

        assertThat(license).isSameAs(License.UNKNOWN);
    }
}
