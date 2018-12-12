package me.proxer.library.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
class DateAdapterTest {

    private DateAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new DateAdapter();
    }

    @Test
    void testFromJsonTimestamp() throws ParseException {
        assertThat(adapter.fromJson("12345")).isEqualTo(new Date(12345 * 1000));
    }

    @Test
    void testFromJsonIso() throws ParseException {
        assertThat(adapter.fromJson("2010-01-01 23:12:10"))
                .isEqualTo(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse("2010-01-01 23:12:10"));
    }

    @Test
    void testFromJsonIsoNoTime() throws ParseException {
        assertThat(adapter.fromJson("2010-01-01"))
                .isEqualTo(new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY).parse("2010-01-01"));
    }

    @Test
    void testFromJsonIsoNoTimeEmpty() throws ParseException {
        assertThat(adapter.fromJson("0000-00-00"))
                .isEqualTo(new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY).parse("0000-00-00"));
    }

    @Test
    void testFromJsonMalformed() {
        assertThatExceptionOfType(ParseException.class).isThrownBy(() -> adapter.fromJson("malformed"));
    }

    @Test
    void testToJson() {
        assertThat(adapter.toJson(new Date(123))).isEqualTo(123);
    }
}
