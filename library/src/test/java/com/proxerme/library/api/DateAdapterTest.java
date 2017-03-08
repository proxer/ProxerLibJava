package com.proxerme.library.api;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class DateAdapterTest {

    private DateAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new DateAdapter();
    }

    @Test
    public void testFromJsonTimestamp() throws Exception {
        assertThat(adapter.fromJson("12345")).isEqualTo(new Date(12345 * 1000));
    }

    @Test
    public void testFromJsonIso() throws Exception {
        assertThat(adapter.fromJson("2010-01-01 23:12:10")).isEqualTo(new Date(1262383930L * 1000));
    }

    @Test
    public void testFromJsonMalformed() throws Exception {
        assertThatExceptionOfType(ParseException.class).isThrownBy(() -> adapter.fromJson("malformed"));
    }

    @Test
    public void testToJson() throws Exception {
        assertThat(adapter.toJson(new Date(123))).isEqualTo(123);
    }
}
