package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectStateTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final ProjectState projectState = api.moshi().adapter(ProjectState.class).fromJson("\"1\"");

        assertThat(projectState).isSameAs(ProjectState.FINISHED);
    }

    @Test
    void testFallback() throws IOException {
        final ProjectState projectState = api.moshi().adapter(ProjectState.class).fromJson("\"xyz\"");

        assertThat(projectState).isSameAs(ProjectState.UNKNOWN);
    }
}
