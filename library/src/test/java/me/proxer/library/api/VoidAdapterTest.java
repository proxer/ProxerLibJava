package me.proxer.library.api;

import com.squareup.moshi.JsonDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
class VoidAdapterTest {

    private VoidAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new VoidAdapter();
    }

    @Test
    void testFromJsonNull() {
        assertThat(adapter.fromJson("null")).isNull();
    }

    @Test
    void testFromJsonInvalid() {
        assertThatExceptionOfType(JsonDataException.class).isThrownBy(() -> adapter.fromJson("value"));
    }

    @Test
    void testToJson() {
        assertThat(adapter.toJson(null)).isEqualTo("null");
    }
}
