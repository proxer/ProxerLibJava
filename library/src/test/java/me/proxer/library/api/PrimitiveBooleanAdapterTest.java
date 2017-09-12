package me.proxer.library.api;

import com.squareup.moshi.JsonDataException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class PrimitiveBooleanAdapterTest {

    private PrimitiveBooleanAdapter adapter;

    @Before
    public void setUp() {
        adapter = new PrimitiveBooleanAdapter();
    }

    @Test
    public void testDefault() {
        assertThat(adapter.fromJson("true")).isTrue();
    }

    @Test
    public void testAlternative() {
        assertThat(adapter.fromJson("1")).isTrue();
    }

    @Test
    public void testDefaultFalse() {
        assertThat(adapter.fromJson("false")).isFalse();
    }

    @Test
    public void testAlternativeFalse() {
        assertThat(adapter.fromJson("0")).isFalse();
    }

    @Test
    public void testInvalid() {
        assertThatExceptionOfType(JsonDataException.class).isThrownBy(() -> adapter.fromJson("malformed"));
    }

    @Test
    public void testInvalidNumber() {
        assertThatExceptionOfType(JsonDataException.class).isThrownBy(() -> adapter.fromJson("3"));
    }
}
