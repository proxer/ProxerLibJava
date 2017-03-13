package com.proxerme.library.api;

import com.squareup.moshi.JsonDataException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class VoidAdapterTest {

    private VoidAdapter adapter;

    @Before
    public void setUp() {
        adapter = new VoidAdapter();
    }

    @Test
    public void testFromJsonNull() {
        assertThat(adapter.fromJson("null")).isNull();
    }

    @Test
    public void testFromJsonInvalid() {
        assertThatExceptionOfType(JsonDataException.class).isThrownBy(() -> adapter.fromJson("value"));
    }
}
