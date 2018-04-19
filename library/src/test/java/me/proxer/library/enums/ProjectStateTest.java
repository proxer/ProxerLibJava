package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectStateTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final ProjectState projectState = api.moshi().adapter(ProjectState.class).fromJson("\"1\"");

        assertThat(projectState).isSameAs(ProjectState.FINISHED);
    }

    @Test
    public void testFallback() throws IOException {
        final ProjectState projectState = api.moshi().adapter(ProjectState.class).fromJson("\"xyz\"");

        assertThat(projectState).isSameAs(ProjectState.UNKNOWN);
    }
}
