package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TagSubTypeTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final TagSubType tagSubType = api.moshi().adapter(TagSubType.class).fromJson("\"story\"");

        assertThat(tagSubType).isSameAs(TagSubType.STORY);
    }

    @Test
    public void testFallback() throws IOException {
        final TagSubType tagSubType = api.moshi().adapter(TagSubType.class).fromJson("\"xyz\"");

        assertThat(tagSubType).isSameAs(TagSubType.OTHER);
    }
}
