package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import me.proxer.library.ProxerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
class NumberBasedBooleanAdapterTest extends ProxerTest {

    private JsonAdapter<Boolean> adapter;

    @Override
    @BeforeEach
    protected void setUp() throws IOException, GeneralSecurityException {
        super.setUp();

        adapter = api.moshi().adapter(Boolean.class, NumberBasedBoolean.class);
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    void testFromBoolean() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("true"))
                .withMessage("Expected a number, string or null but was BOOLEAN at path $");
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    void testFromBooleanFalse() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("false"))
                .withMessage("Expected a number, string or null but was BOOLEAN at path $");
    }

    @Test
    void testFromInt() throws IOException {
        assertThat(adapter.fromJson("1")).isTrue();
    }

    @Test
    void testFromIntFalse() throws IOException {
        assertThat(adapter.fromJson("0")).isFalse();
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    void testFromIntInvalid() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("3"))
                .withMessage("Expected a 1 or 0 but was 3 at path $");
    }

    @Test
    void testFromString() throws IOException {
        assertThat(adapter.fromJson("\"true\"")).isTrue();
    }

    @Test
    void testFromStringFalse() throws IOException {
        assertThat(adapter.fromJson("\"false\"")).isFalse();
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    void testFromStringInvalid() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("\"invalid\""))
                .withMessage("Expected a true or false but was invalid at path $");
    }

    @Test
    void testFromStringInt() throws IOException {
        assertThat(adapter.fromJson("\"1\"")).isTrue();
    }

    @Test
    void testFromStringIntFalse() throws IOException {
        assertThat(adapter.fromJson("\"0\"")).isFalse();
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    void testFromStringIntInvalid() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("\"3\""))
                .withMessage("Expected a 1 or 0 but was 3 at path $");
    }

    @Test
    void testFromNull() throws IOException {
        assertThat(adapter.fromJson("null")).isNull();
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    void testFromInvalid() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("[]"))
                .withMessage("Expected a number, string or null but was BEGIN_ARRAY at path $");
    }

    @Test
    void testToJson() {
        assertThat(adapter.toJson(true)).isEqualTo("1");
    }

    @Test
    void testToJsonFalse() {
        assertThat(adapter.toJson(false)).isEqualTo("0");
    }

    @Test
    void testToJsonNull() {
        assertThat(adapter.toJson(null)).isEqualTo("null");
    }
}
