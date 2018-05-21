package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class BooleanAdapterFactoryTest {

    private BooleanAdapterFactory factory;
    private JsonAdapter<Boolean> adapter;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        factory = new BooleanAdapterFactory();
        adapter = (JsonAdapter<Boolean>) factory.create(Boolean.class, new HashSet<>(), new Moshi.Builder().build());
    }

    @Test
    public void testCreatePrimitive() {
        assertThat(factory.create(Boolean.class, new HashSet<>(), new Moshi.Builder().build())).isNotNull();
    }

    @Test
    public void testCreateBoxed() {
        assertThat(factory.create(boolean.class, new HashSet<>(), new Moshi.Builder().build())).isNotNull();
    }

    @Test
    public void testCreateNonBoolean() {
        assertThat(factory.create(String.class, new HashSet<>(), new Moshi.Builder().build())).isNull();
    }

    @Test
    public void testFromBoolean() throws IOException {
        assertThat(adapter.fromJson("true")).isTrue();
    }

    @Test
    public void testFromBooleanFalse() throws IOException {
        assertThat(adapter.fromJson("false")).isFalse();
    }

    @Test
    public void testFromInt() throws IOException {
        assertThat(adapter.fromJson("1")).isTrue();
    }

    @Test
    public void testFromIntFalse() throws IOException {
        assertThat(adapter.fromJson("0")).isFalse();
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    public void testFromIntInvalid() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("3"))
                .withMessage("Expected a 1 or 0 but was 3 at path $");
    }

    @Test
    public void testFromString() throws IOException {
        assertThat(adapter.fromJson("\"true\"")).isTrue();
    }

    @Test
    public void testFromStringFalse() throws IOException {
        assertThat(adapter.fromJson("\"false\"")).isFalse();
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    public void testFromStringInvalid() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("\"invalid\""))
                .withMessage("Expected a true or false but was invalid at path $");
    }

    @Test
    public void testFromStringInt() throws IOException {
        assertThat(adapter.fromJson("\"1\"")).isTrue();
    }

    @Test
    public void testFromStringIntFalse() throws IOException {
        assertThat(adapter.fromJson("\"0\"")).isFalse();
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    public void testFromStringIntInvalid() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("\"3\""))
                .withMessage("Expected a 1 or 0 but was 3 at path $");
    }

    @Test
    public void testFromNull() throws IOException {
        assertThat(adapter.fromJson("null")).isNull();
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    public void testFromInvalid() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("[]"))
                .withMessage("Expected a boolean, number, string or null but was BEGIN_ARRAY at path $");
    }

    @Test
    public void testToJson() {
        assertThat(adapter.toJson(true)).isEqualTo("true");
    }

    @Test
    public void testToJsonFalse() {
        assertThat(adapter.toJson(false)).isEqualTo("false");
    }

    @Test
    public void testToJsonNull() {
        assertThat(adapter.toJson(null)).isEqualTo("null");
    }
}
