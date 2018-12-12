package me.proxer.library.enums;

import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class RelationshipStatusTest extends ProxerTest {

    @Test
    void testDefault() throws IOException {
        final RelationshipStatus relationshipStatus = api.moshi().adapter(RelationshipStatus.class)
                .fromJson("\"single\"");

        assertThat(relationshipStatus).isSameAs(RelationshipStatus.SINGLE);
    }

    @Test
    void testFallback() throws IOException {
        final RelationshipStatus relationshipStatus = api.moshi().adapter(RelationshipStatus.class)
                .fromJson("\"xyz\"");

        assertThat(relationshipStatus).isSameAs(RelationshipStatus.UNKNOWN);
    }
}
