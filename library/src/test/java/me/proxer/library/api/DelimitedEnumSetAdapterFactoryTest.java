package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.Value;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.MediaLanguage;
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
    void testCreateGenre() {
        final ParameterizedType type = Types.newParameterizedType(Set.class, FskConstraint.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNotNull();
    }

    @Test
    void testCreateFskConstraint() {
        final ParameterizedType type = Types.newParameterizedType(Set.class, FskConstraint.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNotNull();
    }

    @Test
    void testCreateInvalidType() {
        final ParameterizedType type = Types.newParameterizedType(List.class, FskConstraint.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testCreateInvalidParameterType() {
        final ParameterizedType type = Types.newParameterizedType(Set.class, Entry.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testCreateNoParameterType() {
        final JsonAdapter<?> adapter = factory.create(Set.class, Collections.emptySet(), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    void testFromJsonSingle() throws IOException {
        final FskConstrainsTestClass result = moshi.adapter(FskConstrainsTestClass.class)
                .fromJson("{\"fskConstraints\":\"fsk6\"}");

        assertThat(result).isNotNull();
        assertThat(result.fskConstraints).containsExactly(FskConstraint.FSK_6);
    }

    @Test
    void testFromJsonMultiple() throws IOException {
        final FskConstrainsTestClass result = moshi.adapter(FskConstrainsTestClass.class)
                .fromJson("{\"fskConstraints\":\"fsk0 fsk6\"}");

        assertThat(result).isNotNull();
        assertThat(result.fskConstraints).containsExactly(FskConstraint.FSK_0, FskConstraint.FSK_6);
    }

    @Test
    void testFromJsonInvalidDelimiter() throws IOException {
        final MediaLanguageTestClass result = moshi.adapter(MediaLanguageTestClass.class)
                .fromJson("{\"mediaLanguages\":\"de;en\"}");

        assertThat(result).isNotNull();
        assertThat(result.mediaLanguages).containsExactly(MediaLanguage.OTHER);
    }

    @Test
    void testFromJsonEmpty() throws IOException {
        final FskConstrainsTestClass result = moshi.adapter(FskConstrainsTestClass.class)
                .fromJson("{\"fskConstraints\":\"\"}");

        assertThat(result).isNotNull();
        assertThat(result.fskConstraints).isEmpty();
    }

    @Test
    void testFromJsonNull() throws IOException {
        final FskConstrainsTestClass result = moshi.adapter(FskConstrainsTestClass.class)
                .fromJson("{\"fskConstraints\":null}");

        assertThat(result).isNotNull();
        assertThat(result.fskConstraints).isEmpty();
    }

    @Test
    void testFromJsonFallback() throws IOException {
        final MediaLanguageTestClass result = moshi.adapter(MediaLanguageTestClass.class)
                .fromJson("{\"mediaLanguages\":\"de, xyz, zyx\"}");

        assertThat(result).isNotNull();
        assertThat(result.mediaLanguages).containsExactly(MediaLanguage.GERMAN, MediaLanguage.OTHER);
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    void testFromJsonNoFallback() {
        final JsonAdapter<FskConstrainsTestClass> adapter = moshi.adapter(FskConstrainsTestClass.class);

        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("{\"fskConstraints\":\"fsk0 xyz\"}"))
                .withMessageContaining("Expected one of [fsk0")
                .withMessageContaining("but was xyz at path $.fskConstraints");
    }

    @Test
    void testToJsonSingle() {
        final FskConstrainsTestClass testSubject = new FskConstrainsTestClass(EnumSet.of(FskConstraint.FSK_0));
        final String result = moshi.adapter(FskConstrainsTestClass.class).toJson(testSubject);

        assertThat(result).isEqualTo("{\"fskConstraints\":\"fsk0\"}");
    }

    @Test
    void testToJsonMultiple() {
        final MediaLanguageTestClass testSubject = new MediaLanguageTestClass(EnumSet.of(
                MediaLanguage.GERMAN, MediaLanguage.ENGLISH
        ));

        final String result = moshi.adapter(MediaLanguageTestClass.class).toJson(testSubject);

        assertThat(result).isEqualTo("{\"mediaLanguages\":\"de,en\"}");
    }

    @Value
    private static class FskConstrainsTestClass {
        private Set<FskConstraint> fskConstraints;
    }

    @Value
    private static class MediaLanguageTestClass {
        private Set<MediaLanguage> mediaLanguages;
    }
}
