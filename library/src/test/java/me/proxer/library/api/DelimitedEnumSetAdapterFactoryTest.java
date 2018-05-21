package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.Value;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Genre;
import org.junit.Before;
import org.junit.Test;

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
public class DelimitedEnumSetAdapterFactoryTest {

    private DelimitedEnumSetAdapterFactory factory;
    private Moshi moshi;

    @Before
    public void setUp() {
        factory = new DelimitedEnumSetAdapterFactory();
        moshi = new Moshi.Builder()
                .add(factory)
                .build();
    }

    @Test
    public void testCreateGenre() {
        final ParameterizedType type = Types.newParameterizedType(Set.class, Genre.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNotNull();
    }

    @Test
    public void testCreateFskConstraint() {
        final ParameterizedType type = Types.newParameterizedType(Set.class, FskConstraint.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNotNull();
    }

    @Test
    public void testCreateInvalidType() {
        final ParameterizedType type = Types.newParameterizedType(List.class, Genre.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    public void testCreateInvalidParameterType() {
        final ParameterizedType type = Types.newParameterizedType(Set.class, Entry.class);
        final JsonAdapter<?> adapter = factory.create(type, Collections.emptySet(), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    public void testCreateNoParameterType() {
        final JsonAdapter<?> adapter = factory.create(Set.class, Collections.emptySet(), moshi);

        assertThat(adapter).isNull();
    }

    @Test
    public void testFromJsonSingle() throws IOException {
        final GenresTestClass result = moshi.adapter(GenresTestClass.class)
                .fromJson("{\"genres\":\"Abenteuer\"}");

        assertThat(result).isNotNull();
        assertThat(result.genres).containsExactly(Genre.ADVENTURE);
    }

    @Test
    public void testFromJsonMultiple() throws IOException {
        final GenresTestClass result = moshi.adapter(GenresTestClass.class)
                .fromJson("{\"genres\":\"Abenteuer Action\"}");

        assertThat(result).isNotNull();
        assertThat(result.genres).containsExactly(Genre.ADVENTURE, Genre.ACTION);
    }

    @Test
    public void testFromJsonInvalidDelimiter() throws IOException {
        final GenresTestClass result = moshi.adapter(GenresTestClass.class)
                .fromJson("{\"genres\":\"Abenteuer;Action\"}");

        assertThat(result).isNotNull();
        assertThat(result.genres).containsExactly(Genre.UNKNOWN);
    }

    @Test
    public void testFromJsonEmpty() throws IOException {
        final FskConstrainsTestClass result = moshi.adapter(FskConstrainsTestClass.class)
                .fromJson("{\"fskConstraints\":\"\"}");

        assertThat(result).isNotNull();
        assertThat(result.fskConstraints).isEmpty();
    }

    @Test
    public void testFromJsonNull() throws IOException {
        final GenresTestClass result = moshi.adapter(GenresTestClass.class)
                .fromJson("{\"genres\":null}");

        assertThat(result).isNotNull();
        assertThat(result.genres).isEmpty();
    }

    @Test
    public void testFromJsonFallback() throws IOException {
        final GenresTestClass result = moshi.adapter(GenresTestClass.class)
                .fromJson("{\"genres\":\"Action xyz zyx\"}");

        assertThat(result).isNotNull();
        assertThat(result.genres).containsExactly(Genre.ACTION, Genre.UNKNOWN);
    }

    @SuppressWarnings({"CheckReturnValue", "ResultOfMethodCallIgnored"})
    @Test
    public void testFromJsonNoFallback() {
        final JsonAdapter<FskConstrainsTestClass> adapter = moshi.adapter(FskConstrainsTestClass.class);

        assertThatExceptionOfType(JsonDataException.class)
                .isThrownBy(() -> adapter.fromJson("{\"fskConstraints\":\"fsk0 xyz\"}"))
                .withMessageContaining("Expected one of [fsk0")
                .withMessageContaining("but was xyz at path $.fskConstraints");
    }

    @Test
    public void testToJsonSingle() {
        final GenresTestClass testSubject = new GenresTestClass(EnumSet.of(Genre.ACTION));
        final String result = moshi.adapter(GenresTestClass.class).toJson(testSubject);

        assertThat(result).isEqualTo("{\"genres\":\"Action\"}");
    }

    @Test
    public void testToJsonMultiple() {
        final GenresTestClass testSubject = new GenresTestClass(EnumSet.of(Genre.ADVENTURE, Genre.ACTION));
        final String result = moshi.adapter(GenresTestClass.class).toJson(testSubject);

        assertThat(result).isEqualTo("{\"genres\":\"Abenteuer Action\"}");
    }

    @Value
    private static class GenresTestClass {
        private Set<Genre> genres;
    }

    @Value
    private static class FskConstrainsTestClass {
        private Set<FskConstraint> fskConstraints;
    }
}
