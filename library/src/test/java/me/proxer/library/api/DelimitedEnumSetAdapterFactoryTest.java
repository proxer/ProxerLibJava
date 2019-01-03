package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.Value;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
class DelimitedEnumSetAdapterFactoryTest {

    private DelimitedEnumSetAdapterFactory factory;
    private Moshi moshi;

    @BeforeEach
    void setUp() {
        factory = new DelimitedEnumSetAdapterFactory();
        moshi = new Moshi.Builder()
                .add(factory)
                .build();
    }

    @Test
    void testCreate() throws NoSuchFieldException {
        final DelimitedEnumSet annotation = GenderTestClass.class.getField("genders")
                .getAnnotation(DelimitedEnumSet.class);

        final ParameterizedType type = Types.newParameterizedType(Set.class, Gender.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.singleton(annotation), moshi);

        assertThat(adapter).isNotNull();
    }

    @Test
    void testCreateInvalidType() throws NoSuchFieldException {
        final DelimitedEnumSet annotation = GenderTestClass.class.getField("genders")
                .getAnnotation(DelimitedEnumSet.class);

        final ParameterizedType type = Types.newParameterizedType(List.class, FskConstraint.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.singleton(annotation), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testCreateInvalidParameterType() throws NoSuchFieldException {
        final DelimitedEnumSet annotation = GenderTestClass.class.getField("genders")
                .getAnnotation(DelimitedEnumSet.class);

        final ParameterizedType type = Types.newParameterizedType(Set.class, Entry.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.singleton(annotation), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testCreateNoParameterType() throws NoSuchFieldException {
        final DelimitedEnumSet annotation = GenderTestClass.class.getField("genders")
                .getAnnotation(DelimitedEnumSet.class);

        final JsonAdapter<?> adapter = factory.create(Set.class, Collections.singleton(annotation), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testCreateNoAnnotation() {
        final ParameterizedType type = Types.newParameterizedType(Set.class, Gender.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testFromJsonSingle() throws IOException {
        final GenderTestClass result = moshi.adapter(GenderTestClass.class)
                .fromJson("{\"genders\":\"f\"}");

        assertThat(result).isNotNull();
        assertThat(result.genders).containsExactly(Gender.FEMALE);
    }

    @Test
    void testFromJsonMultiple() throws IOException {
        final GenderTestClass result = moshi.adapter(GenderTestClass.class)
                .fromJson("{\"genders\":\"f m\"}");

        assertThat(result).isNotNull();
        assertThat(result.genders).containsExactly(Gender.MALE, Gender.FEMALE);
    }

    @Test
    void testFromJsonInvalidDelimiter() throws IOException {
        final GenderTestClass result = moshi.adapter(GenderTestClass.class)
                .fromJson("{\"genders\":\"f,m\"}");

        assertThat(result).isNotNull();
        assertThat(result.genders).containsExactly(Gender.UNKNOWN);
    }

    @Test
    void testFromJsonEmpty() throws IOException {
        final GenderTestClass result = moshi.adapter(GenderTestClass.class)
                .fromJson("{\"genders\":\"\"}");

        assertThat(result).isNotNull();
        assertThat(result.genders).isEmpty();
    }

    @Test
    void testFromJsonNull() throws IOException {
        final GenderTestClass result = moshi.adapter(GenderTestClass.class)
                .fromJson("{\"genders\":null}");

        assertThat(result).isNotNull();
        assertThat(result.genders).isEmpty();
    }

    @Test
    void testFromJsonDifferentDelimiter() throws IOException {
        final GenderWithDelimiterTestClass result = moshi.adapter(GenderWithDelimiterTestClass.class)
                .fromJson("{\"genders\":\"f, m\"}");

        assertThat(result).isNotNull();
        assertThat(result.genders).containsExactly(Gender.MALE, Gender.FEMALE);
    }

    @Test
    void testFromJsonFallback() throws IOException {
        final GenderTestClass result = moshi.adapter(GenderTestClass.class)
                .fromJson("{\"genders\":\"f m xyz\"}");

        assertThat(result).isNotNull();
        assertThat(result.genders).containsExactly(Gender.MALE, Gender.FEMALE, Gender.UNKNOWN);
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    void testFromJsonNoFallback() {
        final JsonAdapter<FskConstrainTestClass> adapter = moshi.adapter(FskConstrainTestClass.class);

        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("{\"fskConstraints\":\"fsk0 xyz\"}"))
                .withMessageContaining("Expected one of [fsk0")
                .withMessageContaining("but was xyz at path $.fskConstraints");
    }

    @Test
    void testToJsonSingle() {
        final GenderTestClass testSubject = new GenderTestClass(EnumSet.of(Gender.FEMALE));
        final String result = moshi.adapter(GenderTestClass.class).toJson(testSubject);

        assertThat(result).isEqualTo("{\"genders\":\"f\"}");
    }

    @Test
    void testToJsonMultiple() {
        final EnumSet<Gender> values = EnumSet.of(Gender.FEMALE, Gender.MALE);
        final GenderTestClass testSubject = new GenderTestClass(values);
        final String result = moshi.adapter(GenderTestClass.class).toJson(testSubject);

        assertThat(result).isEqualTo("{\"genders\":\"m f\"}");
    }

    @Test
    void testToJsonMultipleDifferentDelimiter() {
        final EnumSet<Gender> values = EnumSet.of(Gender.FEMALE, Gender.MALE);
        final GenderWithDelimiterTestClass testSubject = new GenderWithDelimiterTestClass(values);
        final String result = moshi.adapter(GenderWithDelimiterTestClass.class).toJson(testSubject);

        assertThat(result).isEqualTo("{\"genders\":\"m, f\"}");
    }

    @Value
    private static class GenderTestClass {

        @DelimitedEnumSet
        public Set<Gender> genders;
    }

    @Value
    private static class GenderWithDelimiterTestClass {

        @DelimitedEnumSet(delimiter = ", ")
        public Set<Gender> genders;
    }

    @Value
    private static class FskConstrainTestClass {

        @DelimitedEnumSet
        public Set<FskConstraint> fskConstraints;
    }
}
