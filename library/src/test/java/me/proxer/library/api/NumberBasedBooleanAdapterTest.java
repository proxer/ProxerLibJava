package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import me.proxer.library.ProxerTest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class NumberBasedBooleanAdapterTest extends ProxerTest {

    private JsonAdapter<Boolean> adapter;

    @Override
    @Before
    public void setUp() throws IOException, GeneralSecurityException {
        super.setUp();

        adapter = api.moshi().adapter(Boolean.class, NumberBasedBoolean.class);
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    public void testFromBoolean() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("true"))
                .withMessage("Expected a number, string or null but was BOOLEAN at path $");
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    public void testFromBooleanFalse() {
        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("false"))
                .withMessage("Expected a number, string or null but was BOOLEAN at path $");
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
                .withMessage("Expected a number, string or null but was BEGIN_ARRAY at path $");
    }

    @Test
    public void testToJson() {
        assertThat(adapter.toJson(true)).isEqualTo("1");
    }

    @Test
    public void testToJsonFalse() {
        assertThat(adapter.toJson(false)).isEqualTo("0");
    }

    @Test
    public void testToJsonNull() {
        assertThat(adapter.toJson(null)).isEqualTo("null");
    }
}
