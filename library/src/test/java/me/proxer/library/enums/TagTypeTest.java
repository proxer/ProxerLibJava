package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class TagTypeTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final TagType tagType = api.moshi().adapter(TagType.class).fromJson("\"entry_tag\"");

        assertThat(tagType).isSameAs(TagType.TAG);
    }

    @Test
    void testFallback() throws IOException {
        final TagType tagType = api.moshi().adapter(TagType.class).fromJson("\"xyz\"");

        assertThat(tagType).isSameAs(TagType.OTHER);
    }
}
