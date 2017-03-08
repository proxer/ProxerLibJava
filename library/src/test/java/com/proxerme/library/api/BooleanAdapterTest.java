package com.proxerme.library.api;

import com.squareup.moshi.JsonDataException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class BooleanAdapterTest {

    private BooleanAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new BooleanAdapter();
    }

    @Test
    public void testDefault() throws Exception {
        assertThat(adapter.fromJson("true")).isTrue();
    }

    @Test
    public void testAlternative() throws Exception {
        assertThat(adapter.fromJson("1")).isTrue();
    }

    @Test
    public void testDefaultFalse() throws Exception {
        assertThat(adapter.fromJson("false")).isFalse();
    }

    @Test
    public void testAlternativeFalse() throws Exception {
        assertThat(adapter.fromJson("0")).isFalse();
    }

    @Test
    public void testInvalid() throws Exception {
        assertThatExceptionOfType(JsonDataException.class).isThrownBy(() -> adapter.fromJson("malformed"));
    }
}
