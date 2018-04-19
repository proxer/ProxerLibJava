package me.proxer.library.api;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class DateAdapterTest {

    private DateAdapter adapter;

    @Before
    public void setUp() {
        adapter = new DateAdapter();
    }

    @Test
    public void testFromJsonTimestamp() throws ParseException {
        assertThat(adapter.fromJson("12345")).isEqualTo(new Date(12345 * 1000));
    }

    @Test
    public void testFromJsonIso() throws ParseException {
        assertThat(adapter.fromJson("2010-01-01 23:12:10"))
                .isEqualTo(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse("2010-01-01 23:12:10"));
    }

    @Test
    public void testFromJsonMalformed() {
        assertThatExceptionOfType(ParseException.class).isThrownBy(() -> adapter.fromJson("malformed"));
    }

    @Test
    public void testToJson() {
        assertThat(adapter.toJson(new Date(123))).isEqualTo(123);
    }
}
