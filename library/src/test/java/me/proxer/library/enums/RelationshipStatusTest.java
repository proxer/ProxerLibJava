package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class RelationshipStatusTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException {
        final RelationshipStatus relationshipStatus = api.moshi().adapter(RelationshipStatus.class)
                .fromJson("\"single\"");

        assertThat(relationshipStatus).isSameAs(RelationshipStatus.SINGLE);
    }

    @Test
    public void testFallback() throws IOException {
        final RelationshipStatus relationshipStatus = api.moshi().adapter(RelationshipStatus.class)
                .fromJson("\"xyz\"");

        assertThat(relationshipStatus).isSameAs(RelationshipStatus.UNKNOWN);
    }
}
