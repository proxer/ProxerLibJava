package me.proxer.library.api;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import lombok.Value;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Genre;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(factory.create(Types.newParameterizedType(Set.class, Genre.class),
                Collections.emptySet(), moshi)).isNotNull();
    }

    @Test
    public void testCreateFskConstraint() {
        assertThat(factory.create(Types.newParameterizedType(Set.class, FskConstraint.class),
                Collections.emptySet(), moshi)).isNotNull();
    }

    @Test
    public void testCreateInvalidType() {
        assertThat(factory.create(Types.newParameterizedType(List.class, Genre.class),
                Collections.emptySet(), moshi)).isNull();
    }

    @Test
    public void testCreateInvalidParameterType() {
        assertThat(factory.create(Types.newParameterizedType(Set.class, Entry.class),
                Collections.emptySet(), moshi)).isNull();
    }

    @Test
    public void testCreateNoParameterType() {
        assertThat(factory.create(Set.class, Collections.emptySet(), moshi)).isNull();
    }

    @Test
    public void testFromJsonSingle() throws IOException {
        //noinspection ConstantConditions
        assertThat(moshi.adapter(TestClass.class).fromJson("{\"genre\":\"Abenteuer\"}").genre)
                .containsExactly(Genre.ADVENTURE);
    }

    @Test
    public void testFromJsonMultiple() throws IOException {
        //noinspection ConstantConditions
        assertThat(moshi.adapter(TestClass.class).fromJson("{\"genre\":\"Abenteuer Action\"}").genre)
                .containsExactly(Genre.ADVENTURE, Genre.ACTION);
    }

    @Test
    public void testFromJsonInvalidDelimiter() throws IOException {
        //noinspection ConstantConditions
        assertThat(moshi.adapter(TestClass.class).fromJson("{\"genre\":\"Abenteuer;Action\"}").genre)
                .isEmpty();
    }

    @Test
    public void testFromJsonNull() throws IOException {
        //noinspection ConstantConditions
        assertThat(moshi.adapter(TestClass.class).fromJson("{\"genre\":null}").genre)
                .isEmpty();
    }

    @Test
    public void testToJsonSingle() {
        assertThat(moshi.adapter(TestClass.class).toJson(new TestClass(EnumSet.of(Genre.ACTION))))
                .isEqualTo("{\"genre\":\"Action\"}");
    }

    @Test
    public void testToJsonMultiple() {
        assertThat(moshi.adapter(TestClass.class).toJson(new TestClass(EnumSet.of(Genre.ADVENTURE, Genre.ACTION))))
                .isEqualTo("{\"genre\":\"Abenteuer Action\"}");
    }

    @Value
    private static class TestClass {
        private Set<Genre> genre;
    }
}
