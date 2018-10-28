package me.proxer.library.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.junit.Before;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class BooleanAdapterFactoryTest {

    private BooleanAdapterFactory factory;
    private Moshi moshi;

    private Annotation numberBasedBooleanAnnotation;

    @Before
    public void setUp() {
        factory = new BooleanAdapterFactory();
        moshi = new Moshi.Builder().add(factory).build();

        numberBasedBooleanAnnotation = Dummy.class.getAnnotation(NumberBasedBoolean.class);
    }

    @Test
    public void testCreatePrimitive() {
        final JsonAdapter<?> defaultBooleanAdapter = new Moshi.Builder().build().adapter(boolean.class);

        assertThat(factory.create(boolean.class, Collections.emptySet(), moshi))
                .isNotNull()
                .isInstanceOf(defaultBooleanAdapter.getClass());
    }

    @Test
    public void testCreateBoxed() {
        final JsonAdapter<?> defaultBooleanAdapter = new Moshi.Builder().build().adapter(Boolean.class);

        assertThat(factory.create(Boolean.class, Collections.emptySet(), moshi))
                .isNotNull()
                .isInstanceOf(defaultBooleanAdapter.getClass());
    }

    @Test
    public void testCreatePrimitiveNumberBased() {
        final JsonAdapter<?> defaultBooleanAdapter = new Moshi.Builder().build().adapter(boolean.class);

        assertThat(factory.create(boolean.class, Collections.singleton(numberBasedBooleanAnnotation), moshi))
                .isNotNull()
                .isNotInstanceOf(defaultBooleanAdapter.getClass());
    }

    @Test
    public void testCreateBoxedNumberBased() {
        final JsonAdapter<?> defaultBooleanAdapter = new Moshi.Builder().build().adapter(Boolean.class);

        assertThat(factory.create(Boolean.class, Collections.singleton(numberBasedBooleanAnnotation), moshi))
                .isNotNull()
                .isNotInstanceOf(defaultBooleanAdapter.getClass());
    }

    @Test
    public void testCreateNonBoolean() {
        assertThat(factory.create(String.class, new HashSet<>(), moshi)).isNull();
    }

    @NumberBasedBoolean
    private static class Dummy {
    }
}
