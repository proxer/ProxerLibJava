package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class DelimitedStringSetAdapterFactoryTest {

    private DelimitedStringSetAdapterFactory factory;
    private Moshi moshi;

    @BeforeEach
    void setUp() {
        factory = new DelimitedStringSetAdapterFactory();
        moshi = new Moshi.Builder()
                .add(factory)
                .build();
    }

    @Test
    void testCreate() throws NoSuchFieldException {
        final DelimitedStringSet annotation = StringSetTestClass.class.getField("value")
                .getAnnotation(DelimitedStringSet.class);

        final ParameterizedType type = Types.newParameterizedType(Set.class, String.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.singleton(annotation), moshi);

        assertThat(adapter).isNotNull();
    }

    @Test
    void testCreateInvalidType() throws NoSuchFieldException {
        final DelimitedStringSet annotation = StringSetTestClass.class.getField("value")
                .getAnnotation(DelimitedStringSet.class);

        final ParameterizedType type = Types.newParameterizedType(List.class, String.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.singleton(annotation), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testCreateInvalidParameterType() throws NoSuchFieldException {
        final DelimitedStringSet annotation = StringSetTestClass.class.getField("value")
                .getAnnotation(DelimitedStringSet.class);

        final ParameterizedType type = Types.newParameterizedType(Set.class, Boolean.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.singleton(annotation), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testCreateNoParameterType() throws NoSuchFieldException {
        final DelimitedStringSet annotation = StringSetTestClass.class.getField("value")
                .getAnnotation(DelimitedStringSet.class);

        final JsonAdapter<?> adapter = factory.create(Set.class, Collections.singleton(annotation), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testCreateNoAnnotation() {
        final ParameterizedType type = Types.newParameterizedType(Set.class, String.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testFromJsonSingle() throws IOException {
        final StringSetTestClass result = moshi.adapter(StringSetTestClass.class)
                .fromJson("{\"value\":\"a\"}");

        assertThat(result).isNotNull();
        assertThat(result.value).containsExactly("a");
    }

    @Test
    void testFromJsonMultiple() throws IOException {
        final StringSetTestClass result = moshi.adapter(StringSetTestClass.class)
                .fromJson("{\"value\":\"a b\"}");

        assertThat(result).isNotNull();
        assertThat(result.value).containsExactly("a", "b");
    }

    @Test
    void testFromJsonMultipleSame() throws IOException {
        final StringSetTestClass result = moshi.adapter(StringSetTestClass.class)
                .fromJson("{\"value\":\"a b b\"}");

        assertThat(result).isNotNull();
        assertThat(result.value).containsExactly("a", "b");
    }

    @Test
    void testFromJsonEmpty() throws IOException {
        final StringSetTestClass result = moshi.adapter(StringSetTestClass.class)
                .fromJson("{\"value\":\"\"}");

        assertThat(result).isNotNull();
        assertThat(result.value).isEmpty();
    }

    @Test
    void testFromJsonNull() throws IOException {
        final StringSetTestClass result = moshi.adapter(StringSetTestClass.class)
                .fromJson("{\"value\":null}");

        assertThat(result).isNotNull();
        assertThat(result.value).isEmpty();
    }

    @Test
    void testFromJsonDifferentDelimiter() throws IOException {
        final StringSetWithDelimiterTestClass result = moshi.adapter(StringSetWithDelimiterTestClass.class)
                .fromJson("{\"value\":\"a, b,c\"}");

        assertThat(result).isNotNull();
        assertThat(result.value).containsExactly("a", "b,c");
    }

    @Test
    void testFromJsonValuesToKeep() throws IOException {
        final StringSetWithValuesToKeepTestClass result = moshi.adapter(StringSetWithValuesToKeepTestClass.class)
                .fromJson("{\"value\":\"a b c\"}");

        assertThat(result).isNotNull();
        assertThat(result.value).containsExactly("a b", "c");
    }

    @Test
    void testToJsonSingle() {
        final StringSetTestClass subject = new StringSetTestClass(Collections.singleton("a"));
        final String result = moshi.adapter(StringSetTestClass.class).toJson(subject);

        assertThat(result).isEqualTo("{\"value\":\"a\"}");
    }

    @Test
    void testToJsonMultiple() {
        final StringSetTestClass subject = new StringSetTestClass(new HashSet<>(Arrays.asList("a", "b", "c")));
        final String result = moshi.adapter(StringSetTestClass.class).toJson(subject);

        assertThat(result).isEqualTo("{\"value\":\"a b c\"}");
    }

    @Test
    void testToJsonDifferentDelimiter() {
        final HashSet<String> values = new HashSet<>(Arrays.asList("a", "b", "c"));
        final StringSetWithDelimiterTestClass subject = new StringSetWithDelimiterTestClass(values);
        final String result = moshi.adapter(StringSetWithDelimiterTestClass.class).toJson(subject);

        assertThat(result).isEqualTo("{\"value\":\"a, b, c\"}");
    }

    @Value
    private static class StringSetTestClass {

        @DelimitedStringSet
        public Set<String> value;
    }

    @Value
    private static class StringSetWithDelimiterTestClass {

        @DelimitedStringSet(delimiter = ", ")
        public Set<String> value;
    }

    @Value
    private static class StringSetWithValuesToKeepTestClass {

        @DelimitedStringSet(valuesToKeep = "a b")
        public Set<String> value;
    }
}
