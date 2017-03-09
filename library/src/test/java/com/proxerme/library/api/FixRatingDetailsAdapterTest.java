package com.proxerme.library.api;

import com.proxerme.library.entitiy.info.RatingDetails;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class FixRatingDetailsAdapterTest {

    private FixRatingDetailsAdapter adapter;

    @Before
    public void setUp() {
        adapter = new FixRatingDetailsAdapter();
    }

    @Test
    public void testFromJson() throws IOException {
        assertThat(adapter.fromJson("{\"genre\":\"5\",\"story\":\"5\",\"animation\":\"5\"," +
                "\"characters\":\"5\",\"music\":\"5\"}"))
                .isEqualTo(new RatingDetails(5, 5, 5, 5, 5));
    }

    @Test
    public void testFromJsonPartial() throws IOException {
        assertThat(adapter.fromJson("{\"genre\":\"5\",\"story\":\"5\",\"animation\":\"5\"}"))
                .isEqualTo(new RatingDetails(5, 5, 5, 0, 0));
    }

    @Test
    public void testFromJsonWeirdArray() throws IOException {
        assertThat(adapter.fromJson("[]"))
                .isEqualTo(new RatingDetails(0, 0, 0, 0, 0));
    }

    @Test
    public void testFromJsonInvalid() throws IOException {
        assertThat(adapter.fromJson("{\"invalid\":\"invalid\"}"))
                .isEqualTo(new RatingDetails(0, 0, 0, 0, 0));
    }
}
