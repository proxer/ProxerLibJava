package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TagTypeTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        TagType tagType = api.moshi().adapter(TagType.class).fromJson("\"entry_tag\"");

        assertThat(tagType).isSameAs(TagType.TAG);
    }

    @Test
    public void testFallback() throws IOException {
        TagType tagType = api.moshi().adapter(TagType.class).fromJson("\"xyz\"");

        assertThat(tagType).isSameAs(TagType.OTHER);
    }
}
