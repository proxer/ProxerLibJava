package me.proxer.library.api;

import me.proxer.library.entity.info.RatingDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * @author Ruben Gees
 */
class FixRatingDetailsAdapterTest {

    private FixRatingDetailsAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new FixRatingDetailsAdapter();
    }

    @Test
    void testFromJson() throws IOException {
        assertThat(adapter.fromJson("{\"genre\":\"5\",\"story\":\"5\",\"animation\":\"5\","
                + "\"characters\":\"5\",\"music\":\"5\"}"))
                .isEqualTo(new RatingDetails(5, 5, 5, 5, 5));
    }

    @Test
    void testFromJsonPartial() throws IOException {
        assertThat(adapter.fromJson("{\"genre\":\"5\",\"story\":\"5\",\"animation\":\"5\"}"))
                .isEqualTo(new RatingDetails(5, 5, 5, 0, 0));
    }

    @Test
    void testFromJsonWeirdArray() throws IOException {
        assertThat(adapter.fromJson("[]"))
                .isEqualTo(new RatingDetails(0, 0, 0, 0, 0));
    }

    @Test
    void testFromJsonEmpty() throws IOException {
        assertThat(adapter.fromJson(""))
                .isEqualTo(new RatingDetails(0, 0, 0, 0, 0));
    }

    @Test
    void testFromJsonInvalid() throws IOException {
        assertThat(adapter.fromJson("{\"invalid\":\"invalid\"}"))
                .isEqualTo(new RatingDetails(0, 0, 0, 0, 0));
    }
}
